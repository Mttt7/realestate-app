package com.mt.jwtstarter.controller;


import com.mt.jwtstarter.dto.Message.MessageShortResponseDto;
import com.mt.jwtstarter.dto.Thread.ThreadMessageDetailsResponseDto;
import com.mt.jwtstarter.dto.Thread.ThreadMessageResponseDto;
import com.mt.jwtstarter.service.MessageService;
import com.mt.jwtstarter.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/threads")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ThreadController {
    private final ThreadService threadService;
    private final MessageService messageService;

    @GetMapping("/all")
    public ResponseEntity<Page<ThreadMessageDetailsResponseDto>> getAllThreads(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok(threadService.getAllThreads(pageNumber,pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreadMessageResponseDto> get20lastMessages(@PathVariable Long id, @RequestParam int pageNumber){
        return ResponseEntity.ok(messageService.get20LastMessages(id,pageNumber));
    }
}
