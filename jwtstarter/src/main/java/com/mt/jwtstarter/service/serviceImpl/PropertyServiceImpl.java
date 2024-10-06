package com.mt.jwtstarter.service.serviceImpl;

import com.mt.jwtstarter.dto.Property.PropertyRequestDto;
import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.mapper.PropertiesMapper;
import com.mt.jwtstarter.mapper.StringResponseMapper;
import com.mt.jwtstarter.model.Property;
import com.mt.jwtstarter.model.UserEntity;
import com.mt.jwtstarter.model.UserPropertyFavourite;
import com.mt.jwtstarter.repository.PropertyRepository;
import com.mt.jwtstarter.repository.UserPropertyFavouriteRepository;
import com.mt.jwtstarter.service.AuthService;
import com.mt.jwtstarter.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final AuthService authService;
    private final UserPropertyFavouriteRepository userPropertyFavouriteRepository;
    private final PropertiesMapper propertiesMapper;
    @Override
    public Page<PropertyResponseDto> getAllProperties(int pageSize, int pageNumber) {


        Page<Property> propertiesPage = this.propertyRepository.findAll(PageRequest.of(pageNumber, pageSize));

        List<PropertyResponseDto> propertiesDtoList = propertiesPage
                .stream()
                .map(propertiesMapper::mapToPropertyResponseDto)
                .map(propertyResponseDto -> {
                    UserEntity user = authService.getLoggedUser();
                    boolean exists = userPropertyFavouriteRepository
                            .existsByUserIdAndAndPropertyId(user.getId(),propertyResponseDto.getId());
                    propertyResponseDto.setFavorite(exists);
                    return propertyResponseDto;
                })
                .collect(Collectors.toList());


        return new PageImpl<>(propertiesDtoList, propertiesPage.getPageable(), propertiesPage.getTotalElements());

    }

    @Override
    public Map<String,String> addPropertyToFavourities(Long propertyId) {
        UserEntity user = authService.getLoggedUser();

        boolean exists = userPropertyFavouriteRepository
                .existsByUserIdAndAndPropertyId(user.getId(),propertyId);

        if(exists){
            UserPropertyFavourite userPropertyFavourite = userPropertyFavouriteRepository
                        .findByUserIdAndPropertyId(user.getId(),propertyId);

            userPropertyFavouriteRepository.delete(userPropertyFavourite);
            return Map.of("message","Property removed from favourites");
        }

        Property property = propertyRepository.findById(propertyId).orElseThrow();
        UserPropertyFavourite userPropertyFavourite = new UserPropertyFavourite();

        userPropertyFavourite.setUserId(user.getId());
        userPropertyFavourite.setPropertyId(property.getId());

        userPropertyFavouriteRepository.save(userPropertyFavourite);

        return Map.of("message","Property added to favourites");

    }

    @Override
    public Page<PropertyResponseDto> getFavourites(int pageSize, int pageNumber) {
        UserEntity user = authService.getLoggedUser();
        Page<UserPropertyFavourite> userPropertyFavourites = userPropertyFavouriteRepository
                .findAllByUserId(user.getId(),PageRequest.of(pageNumber,pageSize));

        return new PageImpl<>(
                        userPropertyFavourites
                                .stream()
                                .map(UserPropertyFavourite::getPropertyId)
                                .map(propertyRepository::findById)
                                .filter(java.util.Optional::isPresent)
                                .map(java.util.Optional::get)
                                .map(propertiesMapper::mapToPropertyResponseDto)
                                .map(propertyResponseDto -> {
                                    propertyResponseDto.setFavorite(true);
                                    return propertyResponseDto;
                                })
                                .collect(Collectors.toList()),
                        PageRequest.of(pageNumber,pageSize),
                        userPropertyFavourites.getTotalElements()
                );

    }

    @Override
    public PropertyResponseDto getPropertyById(Long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        PropertyResponseDto propertyResponseDto = propertiesMapper.mapToPropertyResponseDto(property);
        UserEntity user = authService.getLoggedUser();
        boolean exists = userPropertyFavouriteRepository
                .existsByUserIdAndAndPropertyId(user.getId(),propertyResponseDto.getId());
        propertyResponseDto.setFavorite(exists);
        return propertyResponseDto;
    }

    @Override
    public PropertyResponseDto addProperty(PropertyRequestDto property) {

        Property propertyEntity = propertiesMapper.mapToProperty(property);
        propertyEntity.setAuthor(authService.getLoggedUser());
        propertyRepository.save(propertyEntity);
        return propertiesMapper.mapToPropertyResponseDto(propertyEntity);
    }
}
