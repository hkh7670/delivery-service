package com.example.deliveryservice.order.service;


import com.example.deliveryservice.common.exception.BaseException;
import com.example.deliveryservice.common.exception.ErrorCode;
import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getUserOrderList(String startDate, String endDate, String userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Period period;
        try {
            period = Period.between(LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));
        } catch (DateTimeParseException e) {
            throw new BaseException(ErrorCode.INVALID_DATE_FORMAT);
        }
        int dateRange = period.getDays() + 1;
        log.info("dateRange : {}", dateRange);
        if (dateRange > 3) {
            throw new BaseException(ErrorCode.EXCEED_MAX_DATE_RANGE);
        }
        LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).plusDays(1).atStartOfDay();
        return orderRepository.findByUserIdAndRegDateBetween(userId, startDateTime, endDateTime);
    }

    public void modifyOrderInfo(Long orderId) {

    }
}
