package com.example.deliveryservice.order.repository;

import com.example.deliveryservice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.orderDetailList where o.userId = :userId and o.regDate >= :startDate and o.regDate < :endDate ")
    List<Order> findByUserIdAndRegDateBetween(String userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
