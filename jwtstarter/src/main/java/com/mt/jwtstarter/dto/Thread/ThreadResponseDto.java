package com.mt.jwtstarter.dto.Thread;


import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.model.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ThreadResponseDto {

    private Long id;
    private String lastMessage;
    private Timestamp lastMessageDate;
    private UserResponseDto userOne;
    private UserResponseDto userTwo;
}
