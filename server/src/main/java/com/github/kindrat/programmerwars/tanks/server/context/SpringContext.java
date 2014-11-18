package com.github.kindrat.programmerwars.tanks.server.context;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Configuration
@ComponentScan("com.github.kindrat.programmerwars")
@Import({PersistenceContext.class})
public class SpringContext {

    @Bean(name = "config")
    public Config config() {
        return ConfigFactory.load();
    }

    @Bean(name = "serverUri")
    public URI serverUri(Config config) {
        String uri = config.getString("api.uri");
        Integer port = config.getInt("api.port");
        return UriBuilder.fromUri(uri).port(port).build();
    }
}
