package com.smartFarm.was.web.logtrace;

import com.smartFarm.was.web.logtrace.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService threadLocalService = new ThreadLocalService();

    void field() {
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };

        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);

        threadB.start();
        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
