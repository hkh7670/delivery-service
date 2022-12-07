package com.example.deliveryservice.user.entity;

import com.example.deliveryservice.common.domain.Role;
import com.example.deliveryservice.common.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "USER_INFO")
public class UserInfo extends BaseTimeEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NAME")
    private String userName;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Builder
    public UserInfo(String id, String password, String userName, Role role) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }
}