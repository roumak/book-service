package com.this_rc.bookmicroservice.infrastructure.controller;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class ControllerConfig {

    @Bean
    public HomeController homeController(){
        return new HomeController();
    }
}
