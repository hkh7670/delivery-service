package com.example.deliveryservice.config.security;

import com.example.deliveryservice.user.entity.UserInfo;
import com.example.deliveryservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> optional = userRepository.findById(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username + " 사용자 없음");
        } else {
            UserInfo userInfo = optional.get();
            return new SecurityUser(userInfo);
        }

    }

}
