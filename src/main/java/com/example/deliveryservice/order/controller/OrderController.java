package com.example.deliveryservice.order.controller;

import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getUserOrderList(@RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) {
        return ResponseEntity.ok(orderService.getUserOrderList(startDate, endDate));
    }
}
