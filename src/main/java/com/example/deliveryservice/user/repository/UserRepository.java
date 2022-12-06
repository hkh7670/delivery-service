package com.example.deliveryservice.user.repository;

import com.example.deliveryservice.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, String> {
}
