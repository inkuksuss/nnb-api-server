package com.smartFarm.was.domain.v2.concrete_proxy;

import com.smartFarm.was.domain.v2.OrderRepositoryV2;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.TraceStatus;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
