package com.example.deliveryservice.order.repository;

import com.example.deliveryservice.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select distinct o from Order o join fetch o.orderDetailList od join fetch od.menuInfo where o.userId = :userId and o.regDate >= :startDate and o.regDate < :endDate ")
    List<Order> findByUserIdAndRegDateBetween(String userId, LocalDateTime startDate, LocalDateTime endDate);
}
