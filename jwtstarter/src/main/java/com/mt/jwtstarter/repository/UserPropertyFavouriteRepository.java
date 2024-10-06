package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.UserPropertyFavourite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPropertyFavouriteRepository extends JpaRepository<UserPropertyFavourite, Long> {

    Page<UserPropertyFavourite> findAllByUserId(Long id, PageRequest of);

    boolean existsByUserIdAndAndPropertyId(Long userId, Long propertyId);

    UserPropertyFavourite findByUserIdAndPropertyId(Long userId, Long propertyId);
}
