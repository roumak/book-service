package com.this_rc.bookmicroservice.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    @GetMapping("/")
    String getPage(){
        return "forward:index.html";
    }
}
