package com.example.porterounidad.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqQueueConfig {
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
}
