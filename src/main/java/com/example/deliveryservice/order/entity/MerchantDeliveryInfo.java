package com.example.deliveryservice.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MERCHANT_DELIVERY_INFO")
@IdClass(MerchantDeliveryInfoPK.class)
public class MerchantDeliveryInfo {
    @Id
    @Column(name = "ADM_CD")
    private String admCd;
    @Id
    @Column(name = "MERCHANT_ID")
    private Long merchantId;
}
