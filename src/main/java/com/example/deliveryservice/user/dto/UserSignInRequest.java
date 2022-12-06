package com.example.deliveryservice.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
public class UserSignInRequest {
    private String id;
    private String password;
}
