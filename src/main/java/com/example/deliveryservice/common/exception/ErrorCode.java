package com.example.deliveryservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "C000", "Invalid Request Data [%s]"),
//    NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "C001", "Not enough stock"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C002", "Not Found [%s]"),
//    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "C003", "Coupon Not Found"),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "C004", "비밀번호가 올바르지 않습니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "C005", "올바르지 않은 날짜 양식입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C006", "Unauthorized"),
    EXCEED_MAX_DATE_RANGE(HttpStatus.BAD_REQUEST, "C007", "최대 날짜 범위를 초과하였습니다."),
    INVALID_ADDRESS_CHANGE_STATUS(HttpStatus.BAD_REQUEST, "C008", "배달주소를 변경할 수 없는 상태입니다."),
    INVALID_DELIVERY_ADM_CD(HttpStatus.BAD_REQUEST, "C009", "가맹점이 배송가능한 지역이 아닙니다."),
    ALREADY_EXIST_ID(HttpStatus.BAD_REQUEST, "C010", "이미 존재하는 아이디 입니다."),
    TOO_SHORT_PASSWORD(HttpStatus.BAD_REQUEST, "C011", "비밀번호는 12자리 이상이어야 합니다."),
    NOT_EXIST_ADM_CD(HttpStatus.BAD_REQUEST, "C012", "존재하지 않는 법정동 코드 입니다."),
    DELETED_ADM_CD(HttpStatus.BAD_REQUEST, "C013", "폐지된 법정동 코드 입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C999", "Internal Server Error");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}

