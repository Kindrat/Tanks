package com.github.kindrat.programmerwars.server.configuration;

import com.github.kindrat.programmerwars.server.conversion.ConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

@Configuration
public class ConverterConfiguration {

    @Bean
    public ConverterFactory appConverterFactory(Set<Converter> converters) {
        ConverterFactory factory = new ConverterFactory();
        converters.forEach(factory::addConverter);
        return factory;
    }
}
