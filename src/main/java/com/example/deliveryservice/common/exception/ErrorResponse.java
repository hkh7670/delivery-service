package com.example.deliveryservice.common.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private final int status;
    private final String errorCode;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResponse(BaseException e) {
        this.status = e.getStatus().value();
        this.errorCode = e.getErrorCode();
        this.message = e.getMessage();
        this.timestamp = LocalDateTime.now();
    }
}
