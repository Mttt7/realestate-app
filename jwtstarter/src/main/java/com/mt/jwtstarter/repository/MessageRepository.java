package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
