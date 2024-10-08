package com.mt.jwtstarter.dto.Message;


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
    private UserEntity author;
    private String content;
    private Timestamp createdAt;

}
