package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }
    
    //loads order history
    @GetMapping("/orderHistory")
    public String orderHistory(Authentication authentication, Model model) {
        User customer = userRepository.findByUsername(authentication.getName())
                           .orElseThrow(() -> new RuntimeException("User not found")); 
                           //if username cant be found but never really used tbh
        List<Order> orders = orderService.getOrdersForCustomer(customer);
        model.addAttribute("orders", orders);
        return "orderHistory"; // loasd html of it
    }
}
