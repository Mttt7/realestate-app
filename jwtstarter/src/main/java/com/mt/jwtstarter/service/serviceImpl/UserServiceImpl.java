package com.mt.jwtstarter.service.serviceImpl;

import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.exception.UserNotFound;
import com.mt.jwtstarter.mapper.PropertiesMapper;
import com.mt.jwtstarter.mapper.UserMapper;
import com.mt.jwtstarter.model.Property;
import com.mt.jwtstarter.model.UserEntity;
import com.mt.jwtstarter.repository.PropertyRepository;
import com.mt.jwtstarter.repository.UserRepository;
import com.mt.jwtstarter.service.AuthService;
import com.mt.jwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final PropertiesMapper propertiesMapper;

    @Override
    public Long getUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        return user.getId();
    }



    @Override
    public Boolean checkUsernameAvailability(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkEmailAvailability(String email) {
        return !userRepository.existsByEmail(email);
    }



    @Override
    public Page<UserResponseDto> searchForUser(String query, int pageNumber, int pageSize) {
        Page<UserEntity> users = userRepository.findByUsernameOrFirstNameOrLastNameContaining(query,
                PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by("firstName").ascending()
                ));

        return new PageImpl<>(
                users.getContent().stream().map(UserMapper::mapToUserResponseDto).collect(Collectors.toList()),
                PageRequest.of(pageNumber,pageSize),
                users.getTotalElements()
        );
    }

    @Override
    public UserResponseDto getUserDtoById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(
                ()-> new UserNotFound("User Not found!")
        );

        return UserMapper.mapToUserResponseDto(user);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new UserNotFound("User Not found!")
        );
    }

    @Override
    public Page<PropertyResponseDto> getUserProperties(Long id, int pageSize, int pageNumber) {
        Page<Property> properties =  propertyRepository.findAllByAuthorId(id, PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("createdAt").descending()
        ));

        return new PageImpl<>(properties.getContent().stream().map(propertiesMapper::mapToPropertyResponseDto).collect(Collectors.toList()),
                PageRequest.of(pageNumber,pageSize),
                properties.getTotalElements());


    }
}
