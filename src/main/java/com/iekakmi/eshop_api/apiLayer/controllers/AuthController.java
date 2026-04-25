package com.iekakmi.eshop_api.apiLayer.controllers;

import com.iekakmi.eshop_api.dataAccessLayer.models.UserDto;
import com.iekakmi.eshop_api.dataAccessLayer.services.UserService;
import com.iekakmi.eshop_api.apiLayer.models.ResponseContainer;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseContainer<UserDto>> login(@RequestBody UserDto dto) {
        UserDto user = userService.login(dto.getUserName(), dto.getPassWord());
        if (user == null) {
            return new ResponseEntity<>(new ResponseContainer<>(null), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new ResponseContainer<>(user));
    }
}