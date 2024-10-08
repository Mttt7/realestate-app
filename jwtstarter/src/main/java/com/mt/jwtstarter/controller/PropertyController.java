package com.mt.jwtstarter.controller;


import com.mt.jwtstarter.dto.Property.PropertyRequestDto;
import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/property")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/all")
    public ResponseEntity<Page<PropertyResponseDto>> getAllProperties( @RequestParam int pageSize,
                                                                       @RequestParam int pageNumber){
        Page<PropertyResponseDto> properties = propertyService.getAllProperties(pageSize,pageNumber);
        return ResponseEntity.ok(properties);
    }



    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResponseDto> getPropertyById(@PathVariable Long propertyId){
        PropertyResponseDto property = propertyService.getPropertyById(propertyId);
        return ResponseEntity.ok(property);
    }


    @PostMapping("/favourite/{propertyId}")
    public ResponseEntity<Map<String,String>> addPropertyToFavorites(@PathVariable Long propertyId){
        Map<String,String> res = propertyService.addPropertyToFavourities(propertyId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/favourites")
    public ResponseEntity<Page<PropertyResponseDto>> getFavourites(@RequestParam int pageSize,
                                                                   @RequestParam int pageNumber){
        Page<PropertyResponseDto> properties = propertyService.getFavourites(pageSize,pageNumber);
        return ResponseEntity.ok(properties);
    }

    @PostMapping("/add")
    public ResponseEntity<PropertyResponseDto> addProperty(@RequestBody PropertyRequestDto property ){
        PropertyResponseDto res = propertyService.addProperty(property);
        return ResponseEntity.ok(res);
    }
}
