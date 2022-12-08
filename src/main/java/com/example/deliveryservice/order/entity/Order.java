package com.example.deliveryservice.order.entity;

import com.example.deliveryservice.common.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "`order`")
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "MERCHANT_ID")
    private Long merchantId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ADM_CD")
    private String admCd;

//    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private List<OrderDetail> orderDetailList = new ArrayList<>();
}
