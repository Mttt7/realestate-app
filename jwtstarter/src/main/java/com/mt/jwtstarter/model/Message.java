package com.mt.jwtstarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "thread_id")
    private ThreadMessage threadMessage;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "reciver_id")
    private UserEntity receiver;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;


}
