package com.mt.jwtstarter.repository;

import com.mt.jwtstarter.model.ThreadMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ThreadRepository extends JpaRepository<ThreadMessage, Long>{

    @Query("SELECT t FROM ThreadMessage t WHERE t.userOneId = ?1 OR t.userTwoId = ?1")
    Page<ThreadMessage> findAllByUserOneIdOrUserTwoId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT t FROM ThreadMessage t WHERE (t.userOneId = ?1 AND t.userTwoId = ?2) OR (t.userOneId = ?2 AND t.userTwoId = ?1)")
    Optional<ThreadMessage> findByUserOneIdAndUserTwoId(@Param("userOneId") Long userOneId, @Param("userTwoId") Long userTwoId);
}
