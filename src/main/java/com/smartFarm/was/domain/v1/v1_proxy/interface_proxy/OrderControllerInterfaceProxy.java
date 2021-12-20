package com.smartFarm.was.domain.v1.v1_proxy.interface_proxy;

import com.smartFarm.was.domain.v1.OrderControllerV1;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.TraceStatus;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderController.request()");
            String result = target.request(itemId);
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
