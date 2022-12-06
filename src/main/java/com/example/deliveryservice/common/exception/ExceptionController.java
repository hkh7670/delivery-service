package com.example.deliveryservice.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class ExceptionController {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Throw Exception : {}", ex.toString());
        ErrorResponse response = new ErrorResponse(new BaseException(ErrorCode.INTERNAL_SERVER_ERROR));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        log.error("Throw Base Exception : {}", ex.toString());
        ErrorResponse response = new ErrorResponse(ex);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
