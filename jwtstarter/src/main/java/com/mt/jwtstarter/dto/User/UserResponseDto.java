package com.mt.jwtstarter.dto.User;


import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.model.Role;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String about;
    private String photoUrl;

    private Timestamp createdAt;
    private List<Role> roles;
    private List<PropertyResponseDto> properties;

    public void addProperty(PropertyResponseDto property){
        if(properties == null){
            properties = new ArrayList<>();
        }
        properties.add(property);
    }
}
