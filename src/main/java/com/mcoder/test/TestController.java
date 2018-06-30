package com.mcoder.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "test/")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ExecutorService executorService = Executors.newFixedThreadPool(200);

    @GetMapping("/get")
    public Object test() {
        return "123";
    }

    @GetMapping("/cache")
    public void cache() {
        for (int i = 0; i < 1000000; i++) {
            executorService.execute(() -> rabbitTemplate.convertAndSend(Application.queueName, "muscle coder mq test  "));
        }
    }
}
