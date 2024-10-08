package com.mt.jwtstarter.mapper;


import com.mt.jwtstarter.dto.Message.MessageResponseDto;
import com.mt.jwtstarter.dto.Message.MessageShortResponseDto;
import com.mt.jwtstarter.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageMapper {

    public  MessageResponseDto mapToMessageResponseDto(Message message){
        return MessageResponseDto.builder()
                .threadMessage(message.getThreadMessage())
                .content(message.getContent())
                .author(UserMapper.mapToUserResponseDto(message.getAuthor()))
                .receiver(UserMapper.mapToUserResponseDto(message.getReceiver()))
                .createdAt(message.getCreatedAt())
                .id(message.getId())
                .build();
    }

    public MessageShortResponseDto mapToMessageShortResponseDto(Message message){
        return MessageShortResponseDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .authorId(message.getAuthor().getId())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
