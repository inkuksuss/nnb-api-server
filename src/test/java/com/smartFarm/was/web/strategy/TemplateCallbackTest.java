package com.smartFarm.was.web.strategy;


import com.smartFarm.was.web.strategy.code.template.Callback;
import com.smartFarm.was.web.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

    @Test
    void callbackV2() {
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비지니스 로직1 실행"));
        template.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
