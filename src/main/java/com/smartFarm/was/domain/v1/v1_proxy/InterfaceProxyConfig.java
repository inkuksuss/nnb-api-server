package com.smartFarm.was.domain.v1.v1_proxy;


import com.smartFarm.was.domain.v1.*;
import com.smartFarm.was.domain.v1.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.smartFarm.was.domain.v1.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.smartFarm.was.domain.v1.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.smartFarm.was.web.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1 controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }
}
