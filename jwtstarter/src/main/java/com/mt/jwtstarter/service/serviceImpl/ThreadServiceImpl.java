package com.mt.jwtstarter.service.serviceImpl;

import com.mt.jwtstarter.dto.Thread.ThreadResponseDto;
import com.mt.jwtstarter.mapper.ThreadMapper;
import com.mt.jwtstarter.model.ThreadMessage;
import com.mt.jwtstarter.repository.ThreadRepository;
import com.mt.jwtstarter.service.AuthService;
import com.mt.jwtstarter.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThreadServiceImpl implements ThreadService {
    private final ThreadRepository threadRepository;
    private final AuthService authService;
    private final ThreadMapper threadMapper;
    @Override
    public Page<ThreadResponseDto> getAllThreads(int pageNumber, int pageSize) {
        Page<ThreadMessage> response = threadRepository.findAllByUserOneIdOrUserTwoId(
                authService.getLoggedUser().getId(), PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by("lastMessageDate").descending()
                ));

        return new PageImpl<>(
                response.getContent().stream().map(threadMapper::mapToThreadResponseDto).collect(Collectors.toList()),
                PageRequest.of(pageNumber,pageSize),
                response.getTotalElements()
        );

    }
}
