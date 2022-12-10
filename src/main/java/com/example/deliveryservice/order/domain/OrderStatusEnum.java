package com.example.deliveryservice.order.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatusEnum {
    ACCEPTED("접수 완료"),
    COOKING("조리 중"),
    COOKED("조리 완료"),
    IN_DELIVERY("배달 중"),
    DELIVERY_COMPLETED("배달 완료");
    private final String desc;
}
