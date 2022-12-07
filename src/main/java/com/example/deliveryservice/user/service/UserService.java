package com.example.deliveryservice.user.service;

import com.example.deliveryservice.common.domain.Role;
import com.example.deliveryservice.common.exception.BadRequestException;
import com.example.deliveryservice.common.exception.BaseException;
import com.example.deliveryservice.common.exception.ErrorCode;
import com.example.deliveryservice.common.exception.NotFoundException;
import com.example.deliveryservice.config.security.JwtTokenProvider;
import com.example.deliveryservice.user.dto.UserSignInRequest;
import com.example.deliveryservice.user.dto.UserSignUpRequest;
import com.example.deliveryservice.user.entity.UserInfo;
import com.example.deliveryservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUpUser(UserSignUpRequest request) {
        log.info("request : {}", request.toString());
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{12,30}$");
        Matcher matcher = pattern.matcher(request.getPassword());
        if (!matcher.find()) {
            throw new BadRequestException();
        }

        userRepository.save(UserInfo.builder()
                .id(request.getId())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_MEMBER)
                .build());
    }

    public String signInUser(UserSignInRequest request) {
        log.info("request : {}", request.toString());
        UserInfo userInfo = getUserById(request.getId());
        if (!passwordEncoder.matches(request.getPassword(), userInfo.getPassword())) {
            throw new BaseException(ErrorCode.INCORRECT_PASSWORD);
        }

        return jwtTokenProvider.createToken(userInfo.getId(), userInfo.getRole());
    }

    public UserInfo getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
