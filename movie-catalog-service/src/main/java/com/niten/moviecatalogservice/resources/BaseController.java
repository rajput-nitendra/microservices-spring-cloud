package com.niten.moviecatalogservice.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class BaseController {

    @Value("${my.greeting: default value}")
    private String greeting;

    @GetMapping("/config")
    public String getConfigs() {
        return "Greetings: " + greeting;
    }
}
