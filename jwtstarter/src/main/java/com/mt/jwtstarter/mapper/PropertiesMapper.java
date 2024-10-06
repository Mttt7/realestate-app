package com.mt.jwtstarter.mapper;


import com.mt.jwtstarter.dto.Property.PropertyRequestDto;
import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.model.Property;
import com.mt.jwtstarter.model.PropertyImage;
import com.mt.jwtstarter.repository.PropertyImageRepository;
import com.mt.jwtstarter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mt.jwtstarter.mapper.UserMapper.mapToUserResponseDto;

@Service
@RequiredArgsConstructor
public class PropertiesMapper {

    private final AuthService authService;
    private final PropertyImageRepository propertyImageRepository;



    public  PropertyResponseDto mapToPropertyResponseDto(Property property){
            List<PropertyImage> images =  propertyImageRepository.findAllByPropertyId(property.getId());

        return PropertyResponseDto.builder()
                .id(property.getId())
                .name(property.getName())
                .author(mapToUserResponseDto(property.getAuthor()))
                .description(property.getDescription())
                .price(property.getPrice())
                .country(property.getCountry())
                .city(property.getCity())
                .street(property.getStreet())
                .address(property.getAddress())
                .estate(property.getEstate())
                .floor(property.getFloor())
                .floors(property.getFloors())
                .rooms(property.getRooms())
                .bathrooms(property.getBathrooms())
                .sizeMeters(property.getSizeMeters())
                .parkingSpaces(property.getParkingSpaces())
                .createdAt(property.getCreatedAt())
                .lastUpdated(property.getLastUpdated())
                .rent(property.getRent())
                .levelOfFinish(property.getLevelOfFinish())
                .elevator(property.isElevator())
                .yearOfConstruction(property.getYearOfConstruction())
                .heating(property.getHeating())
                .windows(property.getWindows())
                .availableFrom(property.getAvailableFrom())
                .resaleMarket(property.isResaleMarket())
                .balconies(property.getBalconies())
                .images(images)
                .build();
    }

    public  Property mapToProperty(PropertyRequestDto propertyDto){




            Property property = new Property();
            property.setName(propertyDto.getName());
            property.setAuthor(authService.getLoggedUser());
            property.setDescription(propertyDto.getDescription());
            property.setPrice(propertyDto.getPrice());
            property.setCountry(propertyDto.getCountry());
            property.setCity(propertyDto.getCity());
            property.setStreet(propertyDto.getStreet());
            property.setAddress(propertyDto.getAddress());
            property.setEstate(propertyDto.getEstate());
            property.setFloor(propertyDto.getFloor());
            property.setFloors(propertyDto.getFloors());
            property.setRooms(propertyDto.getRooms());
            property.setBathrooms(propertyDto.getBathrooms());
            property.setSizeMeters(propertyDto.getSizeMeters());
            property.setParkingSpaces(propertyDto.getParkingSpaces());
            property.setRent(propertyDto.getRent());
            property.setLevelOfFinish(propertyDto.getLevelOfFinish());
            property.setElevator(propertyDto.isElevator());
            property.setYearOfConstruction(propertyDto.getYearOfConstruction());
            property.setHeating(propertyDto.getHeating());
            property.setWindows(propertyDto.getWindows());
            property.setAvailableFrom(propertyDto.getAvailableFrom());
            property.setResaleMarket(propertyDto.isResaleMarket());
            property.setBalconies(propertyDto.getBalconies());

            propertyDto.getImages().forEach(image -> {
                    PropertyImage propertyImage = new PropertyImage();
                    propertyImage.setUrl(image.getUrl());
                    propertyImage.setImageOrder(image.getOrder());
                    propertyImage.setPropertyId(property.getId());
                    property.addImage(propertyImage);
                    propertyImageRepository.save(propertyImage);
            });

            return property;

    }
}





