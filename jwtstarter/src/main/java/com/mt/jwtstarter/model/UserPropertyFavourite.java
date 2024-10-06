package com.mt.jwtstarter.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "user_property_favourite")
public class UserPropertyFavourite {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;


}
