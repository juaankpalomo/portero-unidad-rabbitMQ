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
    Queue pisoSixHundredAndOneHeader(){
        return new Queue("piso601Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndTwoHeader(){
        return new Queue("piso602Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndThreeHeader(){
        return new Queue("piso603Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndFourHeader(){
        return new Queue("piso604Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndFiveHeader(){
        return new Queue("piso605Queue", false);
    }

    @Bean
    HeadersExchange headersExchange() {return new HeadersExchange("header-exchange");}

    @Bean
    Binding pisoSixHundredAndOneHeaderBinding(Queue pisoSixHundredAndOneHeader, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndOneHeader).to(headerExchange).where("piso").matches("impar");
    }

    @Bean
    Binding pisoSixHundredAndTwoHeaderBinding(Queue pisoSixHundredAndTwoHeader, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndTwoHeader).to(headerExchange).where("piso").matches("par");
    }

    @Bean
    Binding pisoSixHundredAndThreeHeaderBinding(Queue pisoSixHundredAndThreeHeader, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndThreeHeader).to(headerExchange).where("piso").matches("impar");
    }

    @Bean
    Binding pisoSixHundredAndFourHeaderBinding(Queue pisoSixHundredAndFourHeader, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndFourHeader).to(headerExchange).where("piso").matches("par");
    }

    @Bean
    Binding pisoSixHundredAndFiveHeaderBinding(Queue pisoSixHundredAndFiveHeader, HeadersExchange headerExchange) {
        return BindingBuilder.bind(pisoSixHundredAndFiveHeader).to(headerExchange).where("piso").matches("impar");
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
