package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {


    List<PropertyImage> findAllByPropertyId(Long id);
}
