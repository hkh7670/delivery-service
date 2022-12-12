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


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUpUser(UserSignUpRequest request) {
        log.info("request : {}", request.toString());
        // 이미 존재하는 아이디인 경우 exception
        if (userRepository.existsById(request.getId())) {
            throw new BaseException(ErrorCode.ALREADY_EXIST_ID);
        }
        // 비밀번호가 12자리 미만인 경우 exception
        if (request.getPassword().length() < 12) {
            throw new BaseException(ErrorCode.TOO_SHORT_PASSWORD);
        }

        int matchCount = 0;
        // 숫자 포함 유무
        if (request.getPassword().matches(".*[0-9].*")) {
            matchCount++;
        }
        // 영문 소문자 포함 유무
        if (request.getPassword().matches(".*[a-z].*")) {
            matchCount++;
        }
        // 영문 대문자 포함 유무
        if (request.getPassword().matches(".*[A-Z].*")) {
            matchCount++;
        }
        // 특수문자 포함 유무
        if (request.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>~].*")) {
            matchCount++;
        }
        // 3가지 이상 매치되지 않으면 exception
        if (matchCount < 3) {
            throw new BadRequestException("password format");
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
