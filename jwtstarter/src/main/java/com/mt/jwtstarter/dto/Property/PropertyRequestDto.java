package com.mt.jwtstarter.dto.Property;

import com.mt.jwtstarter.dto.PropertyImage.PropertyImagePayload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;


@Data
public class PropertyRequestDto {


    @NotBlank(message = "Name cannot be empty")
    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Price cannot be empty")
    @Pattern(regexp = "^(1000|[1-9][0-9]{3,8})$", message = "Price must be between 1000 and 1000000000")
    private Long price;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Country cannot be empty")
    private String country;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Street cannot be empty")
    private String street;

    private String address; // eg 3/2

    @NotBlank(message = "Estate cannot be empty")
    private String estate; // osiedle

    @NotNull(message = "Floor cannot be empty")
    private int floor;

    @NotNull(message = "Floors cannot be empty")
    private int floors;

    @NotNull(message = "Rooms cannot be empty")
    private int rooms;

    @NotNull(message = "Bathrooms cannot be empty")
    private int bathrooms;

    @NotNull(message = "Windows cannot be empty")
    private String windows;

    @NotNull(message = "Size cannot be empty")
    private int sizeMeters;

    @NotNull(message = "Parking Spaces cannot be empty")
    private int parkingSpaces;

    @NotNull(message = "Resale market status cannot be empty")
    private boolean resaleMarket;

    @NotNull(message = "Balconies count cannot be empty")
    private int balconies;

    @NotNull(message = "Year of construction cannot be empty")
    private int yearOfConstruction;

    @NotNull(message = "Available from date cannot be empty")
    private int availableFrom;

    @NotBlank(message = "Heating cannot be empty")
    private String heating;

    @NotNull(message = "Elevator status cannot be empty")
    private boolean elevator;

    @NotBlank(message = "Level of finish cannot be empty")
    private String levelOfFinish;

    @NotNull(message = "Rent cannot be empty")
    private int rent;

    @Size(min = 5, max = 10, message = "Images count must be between 5 and 10")
    private List<PropertyImagePayload> images;


}
