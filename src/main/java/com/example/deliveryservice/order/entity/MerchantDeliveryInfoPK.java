package com.example.deliveryservice.order.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class MerchantDeliveryInfoPK implements Serializable {
    private String admCd;
    private Long merchantId;
}
