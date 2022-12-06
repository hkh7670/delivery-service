package com.example.deliveryservice.config.security;

import com.example.deliveryservice.user.entity.UserInfo;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private final UserInfo userInfo;

    public SecurityUser(UserInfo userInfo) {
        super(userInfo.getId(), userInfo.getPassword(),
                AuthorityUtils.createAuthorityList(userInfo.getRole().toString()));
        this.userInfo = userInfo;
    }

    public UserInfo getUser() {
        return userInfo;
    }
}