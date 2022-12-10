package com.example.deliveryservice.order.controller;

import com.example.deliveryservice.common.exception.ErrorResponse;
import com.example.deliveryservice.config.security.SecurityUtil;
import com.example.deliveryservice.order.domain.OrderStatusEnum;
import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
@Tag(name = "OrderController")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "배달내역 조회", description = "로그인 계정의 배달내역을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(schema = @Schema(implementation = Order.class))),
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Order.class)))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping()
    public ResponseEntity<List<Order>> getUserOrderList(@RequestParam String startDate, @RequestParam String endDate) {
        String userId = SecurityUtil.getCurrentUserId();
        log.info("userId : {}", userId);
        return ResponseEntity.ok(orderService.getUserOrderList(startDate, endDate, userId));
    }

    @PutMapping("{orderId}")
    public ResponseEntity<Void> modifyOrderInfo(@PathVariable Long orderId) {
        String userId = SecurityUtil.getCurrentUserId();
        log.info("userId : {}", userId);
        log.info("order ID : {}", orderId);
        log.info("order enum : {}", OrderStatusEnum.ACCEPTED);
        orderService.modifyOrderInfo(orderId);
        return ResponseEntity.ok().build();
    }
}
