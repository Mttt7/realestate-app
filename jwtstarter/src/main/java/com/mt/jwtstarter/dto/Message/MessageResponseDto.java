package com.mt.jwtstarter.dto.Message;


import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.model.ThreadMessage;
import com.mt.jwtstarter.model.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class MessageResponseDto {
    private Long id;
    private ThreadMessage threadMessage;
    private UserResponseDto author;
    private UserResponseDto receiver;
    private String content;
    private Timestamp createdAt;

}
