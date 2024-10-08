package com.mt.jwtstarter.controller;


import com.mt.jwtstarter.dto.Thread.ThreadResponseDto;
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

    @GetMapping("/all")
    public ResponseEntity<Page<ThreadResponseDto>> getAllThreads(@RequestParam int pageNumber, @RequestParam int pageSize){
        return ResponseEntity.ok(threadService.getAllThreads(pageNumber,pageSize));

    }
}
