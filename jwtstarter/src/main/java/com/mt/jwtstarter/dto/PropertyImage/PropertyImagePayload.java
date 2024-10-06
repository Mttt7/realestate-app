package com.mt.jwtstarter.dto.PropertyImage;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyImagePayload {
    private int order;
    private String url;
}
