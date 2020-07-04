package com.this_rc.bookmicroservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    String getPage(){
        return "forward:index.html";
    }
}
