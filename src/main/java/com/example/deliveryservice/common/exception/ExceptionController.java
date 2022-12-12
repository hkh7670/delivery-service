package com.example.deliveryservice.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class ExceptionController {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Throw Exception : {}", ex.toString());
        BaseException baseException;
        HttpStatus httpStatus;
        if (ex instanceof DateTimeParseException) {
            baseException = new BaseException(ErrorCode.INVALID_DATE_FORMAT);
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            baseException = new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse response = new ErrorResponse(baseException);
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        log.error("Throw Base Exception : {}", ex.toString());
        ErrorResponse response = new ErrorResponse(ex);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
