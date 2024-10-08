package com.mt.jwtstarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "thread")
public class ThreadMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "last_message_date")
    private Timestamp lastMessageDate;

    @Column(name = "user_one_id")
    private Long userOneId;

    @Column(name = "user_two_id")
    private Long userTwoId;

    public void addMessage(String message){
        this.lastMessage = message;
        this.lastMessageDate = new Timestamp(System.currentTimeMillis());
    }
}
