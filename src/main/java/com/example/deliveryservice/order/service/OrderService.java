package com.example.deliveryservice.order.service;


import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getUserOrderList(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        return orderRepository.findByUserIdAndRegDateBetween("ghhan", startDate, LocalDate.parse(endDate, formatter).plusDays(1).format(formatter));
        return orderRepository.findByUserIdAndRegDateBetween("ghhan", LocalDate.parse(startDate, formatter).atStartOfDay(), LocalDate.parse(endDate, formatter).plusDays(1).atStartOfDay());
    }
}
