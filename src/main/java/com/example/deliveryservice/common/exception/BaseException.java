package com.example.deliveryservice.common.exception;


import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -7806029002430564887L;
    //    private final ErrorCode errorCode;
    private final HttpStatus status;
    private final String errorCode;
    private final String message;

    public BaseException(ErrorCode e) {
        this.status = e.getStatus();
        this.errorCode = e.getErrorCode();
        this.message = e.getMessage();
    }

    public BaseException(ErrorCode e, Object... additional) {
        this.status = e.getStatus();
        this.errorCode = e.getErrorCode();
        this.message = ArrayUtils.isEmpty(additional) ? e.getMessage() : String.format(e.getMessage(), additional);
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
