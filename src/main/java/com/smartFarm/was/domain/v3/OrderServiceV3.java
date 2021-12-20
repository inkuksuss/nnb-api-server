package com.smartFarm.was.domain.v3;

import com.smartFarm.was.domain.v2.OrderRepositoryV2;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;

    public OrderServiceV3(OrderRepositoryV3 orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
