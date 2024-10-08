package com.mt.jwtstarter.service;

import com.mt.jwtstarter.dto.Thread.ThreadMessageDetailsResponseDto;
import com.mt.jwtstarter.model.ThreadMessage;
import org.springframework.data.domain.Page;

public interface ThreadService {
   Page<ThreadMessageDetailsResponseDto> getAllThreads(int pageNumber, int pageSize);

   ThreadMessage createThread(Long id, Long userId);


}
