package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findAllByAuthorId(Long authorId);

    Page<Property> findAllByAuthorId(Long id, PageRequest createdAt);
}
