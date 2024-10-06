package com.mt.jwtstarter.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "address")
    private String address; // eg 3/2

    @Column(name = "estate")
    private String estate; //osiedle

    @Column(name = "floor")
    private int floor;

    @Column(name = "floor_count")
    private int floorCount;

    @Column(name = "floors")
    private int floors;

    @Column(name = "rooms")
    private int rooms;

    @Column(name = "bathrooms")
    private int bathrooms;

    @Column(name = "size_meters")
    private int sizeMeters;

    @Column(name = "parking_spaces")
    private int parkingSpaces;

    @Column(name= "balconies")
    private int balconies;

    @Column(name="resale_market")
    private boolean resaleMarket;

    //months
    @Column(name="available_from")
    private int availableFrom;

    @Column(name="windows")
    private String windows; // "plastic" , "wooden" , "aluminium"

    @Column(name="heating")
    private String heating; // "central" , "electric" , "gas" , "district"

    @Column(name="year_of_construction")
    private int yearOfConstruction;

    @Column(name="elevator")
    private boolean elevator;

// "turnkey" (mieszkanie gotowe do zamieszkania)
// "partially finished" (częściowo wykończone)
// "shell condition" (stan surowy).
    @Column(name="levelOfFinish")
    private String levelOfFinish;

    @Column(name="rent")
    private int rent;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private List<PropertyImage> images;



    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Timestamp lastUpdated;

    public void addImage(PropertyImage image) {
        if (this.images == null) {
            this.images = new ArrayList<>();  // Inicjalizacja mutowalnej listy
        }
        this.images.add(image);  // Dodanie nowego obrazu do listy
    }



}
