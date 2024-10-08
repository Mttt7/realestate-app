package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.threadMessage.id = :id ORDER BY m.createdAt DESC")
    Page<Message> findLast20Messages(Long id, Pageable pageable);
}
