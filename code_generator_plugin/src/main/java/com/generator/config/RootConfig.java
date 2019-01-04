package com.generator.config;

import com.generator.server.HttpRequestHandler;
import com.generator.server.HttpServerInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.generator")
public class RootConfig {

    @Scope("prototype")
    @Bean
    public HttpServerInitializer httpServerInitializer(){
        return new HttpServerInitializer();
    }

    @Scope("prototype")
    @Bean
    public HttpRequestHandler httpRequestHandler(){
        return new HttpRequestHandler();
    }

}
