package com.mt.jwtstarter.mapper;


import com.mt.jwtstarter.dto.Thread.ThreadResponseDto;
import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.model.ThreadMessage;
import com.mt.jwtstarter.model.UserEntity;
import com.mt.jwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThreadMapper {
    private final UserService userService;

    public  ThreadResponseDto mapToThreadResponseDto(ThreadMessage thread){
        UserResponseDto userOne = userService.getUserById(thread.getUserOneId());
        UserResponseDto userTwo = userService.getUserById(thread.getUserTwoId());
        return ThreadResponseDto.builder()
                .id(thread.getId())
                .userOne(userOne)
                .userTwo(userTwo)
                .lastMessage(thread.getLastMessage())
                .lastMessageDate(thread.getLastMessageDate())
                .build();
    }
}
