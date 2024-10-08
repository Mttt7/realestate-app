package com.mt.jwtstarter.dto.Thread;

import com.mt.jwtstarter.dto.Message.MessageShortResponseDto;
import com.mt.jwtstarter.dto.User.UserResponseDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class ThreadMessageResponseDto {

    private Page<MessageShortResponseDto> messages;
    private UserResponseDto userOne;
    private UserResponseDto userTwo;
}
