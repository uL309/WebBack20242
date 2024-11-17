package com.example.imposto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
@EnableRabbit
@SpringBootApplication
public class ImpostoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpostoApplication.class, args);
	}


}
