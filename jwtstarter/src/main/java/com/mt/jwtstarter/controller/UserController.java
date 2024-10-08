package com.mt.jwtstarter.controller;

import com.mt.jwtstarter.dto.Property.PropertyResponseDto;
import com.mt.jwtstarter.dto.User.UserResponseDto;
import com.mt.jwtstarter.model.UserEntity;
import com.mt.jwtstarter.service.AuthService;
import com.mt.jwtstarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final AuthService authService;
    private final UserService userService;


    @GetMapping("/selfId")
    public ResponseEntity<Map<String,Long>> getSelfId(){
        UserEntity user = authService.getLoggedUser();
        Map<String,Long> response = new HashMap<>();
        response.put("id",user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserProfileById(@PathVariable  Long id){
        return new ResponseEntity<>(userService.getUserDtoById(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/properties")
    public ResponseEntity<Page<PropertyResponseDto>> getUserProperties(@PathVariable Long id,
                                                                      @RequestParam int pageSize,
                                                                      @RequestParam int pageNumber){
        return new ResponseEntity<>(userService.getUserProperties(id,pageSize,pageNumber),HttpStatus.OK);
    }
}
