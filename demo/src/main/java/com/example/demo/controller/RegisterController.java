package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserService userService;
    
    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; 
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        try {
            
            Role userRole = Role.valueOf(role.toUpperCase());
            userService.registerUser(username, password, userRole);
            return "redirect:/login?registered=true"; //sends to login if u register
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}

