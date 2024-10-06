package com.mt.jwtstarter.service;

import com.mt.jwtstarter.dto.Property.PropertyRequestDto;
import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PropertyService {
    Page<PropertyResponseDto> getAllProperties(int pageSize, int pageNumber);

    Map<String,String> addPropertyToFavourities(Long propertyId);


    Page<PropertyResponseDto> getFavourites(int pageSize, int pageNumber);

    PropertyResponseDto getPropertyById(Long propertyId);

    PropertyResponseDto addProperty(PropertyRequestDto property);
}
