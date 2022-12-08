package com.example.deliveryservice.order.entity;

import com.example.deliveryservice.common.entity.BaseTimeEntity;
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
@Table(name = "MENU_INFO")
public class MenuInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MERCHANT_ID")
    private Long merchantId;

    @Column(name = "NAME")
    private String name;
}
