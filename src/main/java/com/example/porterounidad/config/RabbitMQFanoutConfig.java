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
 * Clase de configuracion para enviar el correo a todo el piso
 */

@Configuration
public class RabbitMQFanoutConfig {

    @Bean
    Queue pisoSixHundredAndOne(){
        return new Queue("piso601Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndTwo(){
        return new Queue("piso602Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndThree(){
        return new Queue("piso603Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndFour(){
        return new Queue("piso604Queue", false);
    }

    @Bean
    Queue pisoSixHundredAndFive(){
        return new Queue("piso605Queue", false);
    }

    @Bean
    FanoutExchange exchange(){
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding pisoSixHundredAndOneBinding(Queue pisoSixHundredAndOne, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoSixHundredAndOne).to(exchange);
    }

    @Bean
    Binding pisoSixHundredAndTwoBinding(Queue pisoSixHundredAndTwo, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoSixHundredAndTwo).to(exchange);
    }

    @Bean
    Binding pisoSixHundredAndThreeBinding(Queue pisoSixHundredAndThree, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoSixHundredAndThree).to(exchange);
    }

    @Bean
    Binding pisoSixHundredAndFourBinding(Queue pisoSixHundredAndFour, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoSixHundredAndFour).to(exchange);
    }

    @Bean
    Binding pisoSixHundredAndFiveBinding(Queue pisoSixHundredAndFive, FanoutExchange exchange) {
        return BindingBuilder.bind(pisoSixHundredAndFive).to(exchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
