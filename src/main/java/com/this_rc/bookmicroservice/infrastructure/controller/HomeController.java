package com.this_rc.bookmicroservice.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;

class HomeController {
    @GetMapping("/")
    String getPage(){
        return "forward:index.html";
    }

    @GetMapping
    String getVal(){
        return "Hi Roumak";
    }
}
