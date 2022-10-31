package com.example.porterounidad.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion para enviar el correo a los apartamentos impares
 */

@Configuration
public class RabbitMQHeaderConfig {


    @Bean
    HeadersExchange headersExchange() {return new HeadersExchange("header-exchange");}

    @Bean
    Binding pisoSixHundredAndOneHeaderBinding(Queue pisoSixHundredAndOne, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndOne).to(headerExchange).where("apto").matches("impar");
    }

    @Bean
    Binding pisoSixHundredAndTwoHeaderBinding(Queue pisoSixHundredAndTwo, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndTwo).to(headerExchange).where("apto").matches("par");
    }

    @Bean
    Binding pisoSixHundredAndThreeHeaderBinding(Queue pisoSixHundredAndThree, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndThree).to(headerExchange).where("apto").matches("impar");
    }

    @Bean
    Binding pisoSixHundredAndFourHeaderBinding(Queue pisoSixHundredAndFour, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndFour).to(headerExchange).where("apto").matches("par");
    }

    @Bean
    Binding pisoSixHundredAndFiveHeaderBinding(Queue pisoSixHundredAndFive, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndFive).to(headerExchange).where("apto").matches("impar");
    }

    @Bean
    public MessageConverter jsonMessageConverterHeader() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainerHeader(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverterHeader());
        return rabbitTemplate;
    }


}
