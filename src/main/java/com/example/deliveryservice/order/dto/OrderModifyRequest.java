package com.example.deliveryservice.order.dto;

import com.example.deliveryservice.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModifyRequest {
    private String admCd;
    private String baseAddress;
    private String detailAddress;

}
