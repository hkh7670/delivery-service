package com.example.deliveryservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "C000", "Invalid Request Data [%s]"),
    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "C001", "Not enough stock"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C002", "Not Found [%s]"),
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "Coupon Not Found"),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "C004", "Incorrect Password"),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "C005", "Invalid Date Format"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C006", "Unauthorized"),
    EXCEED_MAX_DATE_RANGE(HttpStatus.BAD_REQUEST, "C007", "Exceed maximum date range"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C999", "Internal Server Error");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}

