package com.mt.jwtstarter.dto.Property;


import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.model.PropertyImage;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class PropertyResponseDto {

    private Long id;

    private UserResponseDto author;

    private String name;
    private Long price;
    private String description;
    private String country;
    private String city;
    private String street;
    private String address; // eg 3/2
    private String estate; //osiedle
    private int floor;
    private int floors;
    private int rooms;
    private int bathrooms;
    private int sizeMeters;
    private int parkingSpaces;

    private boolean favorite;
    private int balconies;
    private boolean resaleMarket;
    private int availableFrom;
    private String windows;
    private String heating;
    private int yearOfConstruction;
    private boolean elevator;
    private String levelOfFinish;
    private int rent;
    private List<PropertyImage> images;

    private Timestamp createdAt;
    private Timestamp lastUpdated;
}
