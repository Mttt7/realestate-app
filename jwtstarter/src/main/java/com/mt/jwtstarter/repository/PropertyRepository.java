package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByAuthorId(Long authorId);

}
