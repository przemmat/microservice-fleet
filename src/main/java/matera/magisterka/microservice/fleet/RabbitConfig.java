package matera.magisterka.microservice.fleet;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "fleet.exchange";

    @Bean
    public TopicExchange fleetExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}