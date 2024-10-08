package com.mt.jwtstarter.service;



import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.dto.User.UserResponseDto;
import org.springframework.data.domain.Page;

public interface UserService {

    Long getUserId();



    Boolean checkUsernameAvailability(String username);

    Boolean checkEmailAvailability(String email);

    Page<UserResponseDto> searchForUser(String query, int pageNumber, int pageSize);


    UserResponseDto getUserById(Long id);

    Page<PropertyResponseDto> getUserProperties(Long id, int pageSize, int pageNumber);
}
