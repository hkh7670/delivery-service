package com.example.deliveryservice.common.exception;


import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException {

    public UnauthorizedException(Object... additional) {
        super(ErrorCode.UNAUTHORIZED, additional);
    }

}
