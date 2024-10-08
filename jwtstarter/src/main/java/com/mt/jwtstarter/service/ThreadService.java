package com.mt.jwtstarter.service;

import com.mt.jwtstarter.dto.Thread.ThreadResponseDto;
import org.springframework.data.domain.Page;

public interface ThreadService {
   Page<ThreadResponseDto> getAllThreads(int pageNumber, int pageSize);
}
