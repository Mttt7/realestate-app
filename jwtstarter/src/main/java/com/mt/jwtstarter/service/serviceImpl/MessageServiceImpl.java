package com.mt.jwtstarter.service.serviceImpl;

import com.mt.jwtstarter.dto.Message.MessageResponseDto;
import com.mt.jwtstarter.dto.Message.MessageShortResponseDto;
import com.mt.jwtstarter.dto.Thread.ThreadMessageResponseDto;
import com.mt.jwtstarter.mapper.MessageMapper;
import com.mt.jwtstarter.model.Message;
import com.mt.jwtstarter.model.ThreadMessage;
import com.mt.jwtstarter.model.UserEntity;
import com.mt.jwtstarter.repository.MessageRepository;
import com.mt.jwtstarter.repository.ThreadRepository;
import com.mt.jwtstarter.service.AuthService;
import com.mt.jwtstarter.service.MessageService;
import com.mt.jwtstarter.service.ThreadService;
import com.mt.jwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final ThreadService threadService;
    private final ThreadRepository threadRepository;
    private final MessageRepository messageRepository;
    private final AuthService authService;
    private final MessageMapper messageMapper;
    private final UserService userService;
    @Override
    public MessageResponseDto sendMessageToUser(Long userId, String message) {
        UserEntity loggedUser = authService.getLoggedUser();
        ThreadMessage threadMessage = threadRepository.findByUserOneIdAndUserTwoId(loggedUser.getId(),userId)
                .orElseGet(()-> threadService.createThread(loggedUser.getId(),userId));

        Message newMessage = new Message();
        newMessage.setThreadMessage(threadMessage);
        newMessage.setContent(message);
        newMessage.setAuthor(loggedUser);
        newMessage.setReceiver(userService.getUserById(userId));
        messageRepository.save(newMessage);

        threadMessage.setLastMessage(newMessage.getContent());
        threadMessage.setLastMessageDate(newMessage.getCreatedAt());
        threadRepository.save(threadMessage);

        return messageMapper.mapToMessageResponseDto(newMessage);

    }

    @Override
    public ThreadMessageResponseDto get20LastMessages(Long id, int pageNumber) {
        ThreadMessage threadMessage = threadRepository.findById(id).orElseThrow(()->new RuntimeException("Thread not found"));
        Page<Message> messages = messageRepository.findLast20Messages(id, PageRequest.of(pageNumber,20));

        return ThreadMessageResponseDto.builder()
                .messages(new PageImpl<>(
                    messages.getContent().stream().map(messageMapper::mapToMessageShortResponseDto).collect(Collectors.toList()),
                    PageRequest.of(pageNumber,20),
                    messages.getTotalElements()))
                .userOne(userService.getUserDtoById(threadMessage.getUserOneId()))
                .userTwo(userService.getUserDtoById(threadMessage.getUserTwoId()))
                .build();

    }
}
