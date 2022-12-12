package com.example.deliveryservice.order.entity;

import com.example.deliveryservice.common.entity.BaseTimeEntity;
import com.example.deliveryservice.order.domain.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @Column(name = "BASE_ADDRESS")
    private String baseAddress;

    @Column(name = "DETAIL_ADDRESS")
    private String detailAddress;

    @Column(name = "ADM_CD")
    private String admCd;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

//    @JsonIgnore
    @OneToMany()
    @JoinColumn(name = "ORDER_ID")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MERCHANT_ID", insertable = false, updatable = false)
    private Merchant merchant;

    public void setAddressAndAdmCd(String baseAddress, String detailAddress, String admCd) {
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.admCd = admCd;
    }
}
