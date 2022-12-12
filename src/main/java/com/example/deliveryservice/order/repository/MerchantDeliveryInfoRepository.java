package com.example.deliveryservice.order.repository;

import com.example.deliveryservice.order.entity.MerchantDeliveryInfo;
import com.example.deliveryservice.order.entity.MerchantDeliveryInfoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantDeliveryInfoRepository extends JpaRepository<MerchantDeliveryInfo, MerchantDeliveryInfoPK> {

    List<MerchantDeliveryInfo> findByMerchantId(Long merchantId);

}
