package com.mt.jwtstarter.controller;


import com.mt.jwtstarter.dto.Message.MessageRequestDto;
import com.mt.jwtstarter.dto.Message.MessageResponseDto;
import com.mt.jwtstarter.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/send/{userId}")
    public ResponseEntity<MessageResponseDto> sendMessageToUser(@PathVariable Long userId, @RequestBody MessageRequestDto messageRequestDto){
        return ResponseEntity.ok().body(messageService.sendMessageToUser(userId,messageRequestDto.getContent()));
    }
}
