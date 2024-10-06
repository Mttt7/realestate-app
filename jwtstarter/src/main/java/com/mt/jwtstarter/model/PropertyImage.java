package com.mt.jwtstarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_image")
public class PropertyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "url")
    private String url;

    @Column(name = "image_order")
    private int imageOrder; //1-10
}
