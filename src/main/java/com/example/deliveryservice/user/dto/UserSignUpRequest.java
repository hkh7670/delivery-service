package com.example.deliveryservice.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignUpRequest {
    private String id;
    private String password;
    private String userName;
}
