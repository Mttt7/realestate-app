package com.mt.jwtstarter.dto.Message;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class MessageShortResponseDto {
    private Long id;
    private String content;
    private Long authorId;
    private Timestamp createdAt;
}
