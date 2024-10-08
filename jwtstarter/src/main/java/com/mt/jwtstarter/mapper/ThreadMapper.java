package com.mt.jwtstarter.mapper;


import com.mt.jwtstarter.dto.Thread.ThreadMessageDetailsResponseDto;
import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.model.ThreadMessage;
import com.mt.jwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThreadMapper {
    private final UserService userService;

    public ThreadMessageDetailsResponseDto mapToThreadResponseDto(ThreadMessage thread){
        UserResponseDto userOne = userService.getUserDtoById(thread.getUserOneId());
        UserResponseDto userTwo = userService.getUserDtoById(thread.getUserTwoId());
        return ThreadMessageDetailsResponseDto.builder()
                .id(thread.getId())
                .userOne(userOne)
                .userTwo(userTwo)
                .lastMessage(thread.getLastMessage())
                .lastMessageDate(thread.getLastMessageDate())
                .build();
    }
}
