package de.rauldev.masterspring.orderapi.config;

import de.rauldev.masterspring.orderapi.converters.OrderConverter;
import de.rauldev.masterspring.orderapi.converters.ProductConverter;
import de.rauldev.masterspring.orderapi.converters.UserConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class ConverterConfig {
    @Value("${config.dateTimeFormat}")
    private String dateTimeFormat;

    @Bean
    public ProductConverter getProductConverter(){
        return new ProductConverter();
    }

    @Bean
    public OrderConverter getOrderConverter(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
        return new OrderConverter(formatter,getProductConverter(),getUserConverter());
    }

    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }
}
