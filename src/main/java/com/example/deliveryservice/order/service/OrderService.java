package com.example.deliveryservice.order.service;


import com.example.deliveryservice.common.exception.BaseException;
import com.example.deliveryservice.common.exception.ErrorCode;
import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getUserOrderList(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        try {
            startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
            endDateTime = LocalDate.parse(endDate, formatter).plusDays(1).atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new BaseException(ErrorCode.INVALID_DATE_FORMAT);
        }
        return orderRepository.findByUserIdAndRegDateBetween("ghhan", startDateTime, endDateTime);
    }
}
