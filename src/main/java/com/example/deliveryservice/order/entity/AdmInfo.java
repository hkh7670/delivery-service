package com.example.deliveryservice.order.entity;

import com.example.deliveryservice.order.domain.YnEnum;
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
@Table(name = "ADM_INFO")
// 법정동 전체 코드 리스트 테이블
public class AdmInfo {
    @Id
    @Column(name = "CD")
    private String cd;

    @Column(name = "ADM_NM")
    private String admNm;

    @Column(name = "DELETE_YN")
    @Enumerated(EnumType.STRING)
    private YnEnum deleteYn;
}
