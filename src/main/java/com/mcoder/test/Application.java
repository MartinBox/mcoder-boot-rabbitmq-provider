package com.mcoder.test;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    static final String queueName = "mcoder-boot-rabbitmq-queue";

    static final String topicExchangeName = "mcoder-boot-rabbitmq-exchange";

    static final String routingKey = "mcoder.rabbitmq.key";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /*@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.1.8", 5672);

        connectionFactory.setUsername("muscle");
        connectionFactory.setPassword("coder");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);

        return connectionFactory;
    }*/

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}
