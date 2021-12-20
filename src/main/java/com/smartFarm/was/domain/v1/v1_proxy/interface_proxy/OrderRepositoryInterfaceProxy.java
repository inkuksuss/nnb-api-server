package com.smartFarm.was.domain.v1.v1_proxy.interface_proxy;

import com.smartFarm.was.domain.v1.OrderRepositoryV1;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 target;
    private final LogTrace logTrace;

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
