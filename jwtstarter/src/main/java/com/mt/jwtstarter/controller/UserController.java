package com.mt.jwtstarter.controller;

import com.mt.jwtstarter.model.UserEntity;
import com.mt.jwtstarter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final AuthService authService;


    @GetMapping("/selfId")
    public ResponseEntity<Map<String,Long>> getSelfId(){
        UserEntity user = authService.getLoggedUser();
        Map<String,Long> response = new HashMap<>();
        response.put("id",user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
