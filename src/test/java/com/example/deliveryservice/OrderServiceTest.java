package com.example.deliveryservice;

import com.example.deliveryservice.common.exception.BaseException;
import com.example.deliveryservice.order.dto.OrderModifyRequest;
import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.repository.OrderRepository;
import com.example.deliveryservice.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("배달 조회 테스트")
    @Transactional
    void getOrderListTest() {
        // given
        // when
        List<Order> orderList = orderService.getUserOrderList("20221208", "20221210", "ghhan");
        // then
        assertEquals(orderList.size(), 2);
    }

    @Test
    @DisplayName("배달 주소 수정 테스트")
    @Transactional
    void modifyOrderInfoTest() {
        // given
        OrderModifyRequest request = new OrderModifyRequest();
        request.setAdmCd("4119011000");
        request.setBaseAddress("경기도 부천시 소사본동 TEST");
        request.setDetailAddress("10-101010 TEST");
        // when
        orderService.modifyOrderInfo(2L, "ghhan", request);
        Order order = orderRepository.findById(2L).get();
        // then
        assertEquals(order.getAdmCd(), "4119011000");
    }

}
