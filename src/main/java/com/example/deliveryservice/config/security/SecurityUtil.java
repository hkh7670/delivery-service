package com.example.deliveryservice.config.security;

import com.example.deliveryservice.common.exception.UnauthorizedException;
import com.example.deliveryservice.user.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {
    public static Authentication getAuthentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication : {}", authentication);
        if (authentication == null) {
            throw new UnauthorizedException();
        }
        if (!authentication.isAuthenticated()) {
            throw new UnauthorizedException();
        }
        return authentication;
    }

    public static String getCurrentUsername() {
        Authentication authentication = getAuthentication();
        SecurityUser springSecurityUser = (SecurityUser) authentication.getPrincipal();
        UserInfo userInfo = springSecurityUser.getUser();
        return userInfo.getUserName();
    }

    public static String getCurrentUserId() {
        Authentication authentication = getAuthentication();
        SecurityUser springSecurityUser = (SecurityUser) authentication.getPrincipal();
        UserInfo userInfo = springSecurityUser.getUser();
        return userInfo.getId();
    }
}
