package com.example.porterounidad.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="broker/")
public class ResourceREST {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping(value = "/producer/pisos")
    public Mono<String> producerPisos(@RequestParam("exchangeName") String exchange,
                                      @RequestParam("messageData") String messageData){
        amqpTemplate.convertAndSend(exchange, "", messageData);
        return Mono.just("Correo repartido a todo el piso");
    }

    @GetMapping(value = "/producer/apto/impares")
    public Mono<String> producer(@RequestParam("exchangeName") String exchange, @RequestParam("apto") String apto,
                           @RequestParam("messageData") String messageData) {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("apto", apto);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(messageData, messageProperties);
        amqpTemplate.send(exchange, "", message);

        return Mono.just("Message sent to the RabbitMQ Header Exchange Successfully");
    }

}
