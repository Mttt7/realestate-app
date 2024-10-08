package com.mt.jwtstarter.service;

import com.mt.jwtstarter.dto.Message.MessageResponseDto;
import com.mt.jwtstarter.dto.Message.MessageShortResponseDto;
import com.mt.jwtstarter.dto.Thread.ThreadMessageResponseDto;
import org.springframework.data.domain.Page;

public interface MessageService {
    MessageResponseDto sendMessageToUser(Long userId, String message);

    ThreadMessageResponseDto get20LastMessages(Long id, int pageNumber);
}
