package com.example.deliveryservice.order.service;


import com.example.deliveryservice.common.exception.BaseException;
import com.example.deliveryservice.common.exception.ErrorCode;
import com.example.deliveryservice.common.exception.NotFoundException;
import com.example.deliveryservice.order.domain.OrderStatusEnum;
import com.example.deliveryservice.order.domain.YnEnum;
import com.example.deliveryservice.order.dto.OrderModifyRequest;
import com.example.deliveryservice.order.entity.AdmInfo;
import com.example.deliveryservice.order.entity.MerchantDeliveryInfo;
import com.example.deliveryservice.order.entity.Order;
import com.example.deliveryservice.order.repository.AdmInfoRepository;
import com.example.deliveryservice.order.repository.MerchantDeliveryInfoRepository;
import com.example.deliveryservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final MerchantDeliveryInfoRepository merchantDeliveryInfoRepository;
    private final AdmInfoRepository admInfoRepository;

    public List<Order> getUserOrderList(String startDate, String endDate, String userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);
        Period period = Period.between(localStartDate, localEndDate);
        int dateRange = period.getDays() + 1;
        log.info("dateRange : {}", dateRange);

        if (dateRange > 3) {
            throw new BaseException(ErrorCode.EXCEED_MAX_DATE_RANGE);
        }

        return orderRepository.findByUserIdAndRegDateBetween(userId, localStartDate.atStartOfDay(), localEndDate.plusDays(1).atStartOfDay());
    }

    public void modifyOrderInfo(Long orderId, String userId, OrderModifyRequest request) {
        Order order = orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(() -> new NotFoundException("orderId: " + orderId + ", userId: " + userId));
        // 배달 중 이거나 이미 배달이 완료된 경우 주소 변경 불가
        if (!isChangeableAddress(order.getStatus())) {
            throw new BaseException(ErrorCode.INVALID_ADDRESS_CHANGE_STATUS);
        }

        // 가맹점이 배달 가능한 법정동 리스트
        List<MerchantDeliveryInfo> merchantDeliveryInfoList = merchantDeliveryInfoRepository.findByMerchantId(order.getMerchantId());

        // 주소변경 요청한 법정동 코드가 가맹점에서 배달 불가능한 지역인 경우 주소 변경 불가
        merchantDeliveryInfoList.stream().filter((item) -> item.getAdmCd().equals(request.getAdmCd())).findFirst().orElseThrow(() ->
                new BaseException(ErrorCode.INVALID_DELIVERY_ADM_CD));
        // ADM_INFO 테이블(법정동 전체 코드 리스트)에 존재하지 않는 법정동코드일 경우 exception
        AdmInfo admInfo = admInfoRepository.findById(request.getAdmCd()).orElseThrow(() -> new BaseException(ErrorCode.NOT_EXIST_ADM_CD));
        // 폐지된 법정동일 경우 exception
        if (admInfo.getDeleteYn() == YnEnum.Y) {
            throw new BaseException(ErrorCode.DELETED_ADM_CD);
        }
        order.setAddressAndAdmCd(request.getBaseAddress(), request.getDetailAddress(), request.getAdmCd());
        orderRepository.save(order);
    }

    public boolean isChangeableAddress(OrderStatusEnum status) {
        return status != OrderStatusEnum.IN_DELIVERY && status != OrderStatusEnum.DELIVERY_COMPLETED;
    }

    public Map searchAddressInfo(String keyword, String currentPage, String countPerPage) {
        WebClient webClient = WebClient.create("https://business.juso.go.kr/addrlink/addrLinkApi.do");
        return webClient.get()
                .uri(urlBuilder -> urlBuilder
                        .queryParam("keyword", keyword)
                        .queryParam("resultType", "json")
                        .queryParam("confmKey", "devU01TX0FVVEgyMDIyMTIwNzE0NDgyMzExMzMwMTc=")
                        .queryParam("currentPage", currentPage)
                        .queryParam("countPerPage", countPerPage)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block()
                ;
    }


}
