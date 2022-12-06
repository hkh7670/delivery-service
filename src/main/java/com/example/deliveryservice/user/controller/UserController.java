package com.example.deliveryservice.user.controller;

import com.example.deliveryservice.user.service.UserService;
import com.example.deliveryservice.user.dto.UserSignInRequest;
import com.example.deliveryservice.user.dto.UserSignUpRequest;
import com.example.deliveryservice.user.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<Void> signUpUser(@RequestBody UserSignUpRequest request) {
        userService.signUpUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 로그인
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserSignInRequest request) {
        return ResponseEntity.ok(userService.signInUser(request));
    }



    @GetMapping("info/{id}")
    public ResponseEntity<UserInfo> signUpUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
