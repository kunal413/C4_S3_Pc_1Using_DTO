package com.niit.jap.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchangeName="movie-exchange";
    private String registerQueue="movie.queue";
    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue registerQueue()
    {
        return new Queue(registerQueue,true);
    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter()    {
        return new  Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemp=new RabbitTemplate(connectionFactory);
        rabbitTemp.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemp;
    }
    @Bean
    Binding bindingUser(DirectExchange exchange,Queue registerQueue)
    {
        return BindingBuilder.bind(registerQueue()).to(exchange).with("movie-routing");
    }
}
