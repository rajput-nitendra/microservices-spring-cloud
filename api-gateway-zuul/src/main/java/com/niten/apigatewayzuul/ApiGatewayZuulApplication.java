package com.niten.apigatewayzuul;

import com.niten.apigatewayzuul.filter.LoggingZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ApiGatewayZuulApplication {

    @Bean
    public LoggingZuulFilter loggingZuulFilter() {
        return new LoggingZuulFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayZuulApplication.class, args);
    }

}
