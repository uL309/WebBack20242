package com.example.builder;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @RabbitListener(queues = {"teste"})
    public String receive(@Payload String fileBody) {
        return fileBody;
    }

    @RabbitListener(queues = {"testeid"})
    public String receiveid(@Payload String fileBody) {
        return "Message: " + fileBody;
    }
}
