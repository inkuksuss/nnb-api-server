package com.smartFarm.was.web.proxy.pureproxy.concreteproxy;


import com.smartFarm.was.web.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import com.smartFarm.was.web.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import com.smartFarm.was.web.proxy.pureproxy.concreteproxy.code.TimeProxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);
        concreteClient.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();

    }
}
