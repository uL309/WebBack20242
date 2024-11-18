package com.example.hub.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String nome_fila, String order) {
        this.rabbitTemplate.convertAndSend(nome_fila, order);
    }

    public void sendLong(String nome_fila, long order) {
        this.rabbitTemplate.convertAndSend(nome_fila, order);
    }
}
