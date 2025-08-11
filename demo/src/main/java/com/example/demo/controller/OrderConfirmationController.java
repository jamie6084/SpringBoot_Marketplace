package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderConfirmationController {

    @GetMapping("/orderConfirmation")
    public String orderConfirmation() {
        
        return "orderConfirmation"; //to load html file below
    }
}

