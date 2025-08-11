package com.example.demo.controller;

import com.example.demo.service.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class LoginController {

    private final CustomUserDetailsService userDetailsService;

    public LoginController(CustomUserDetailsService userDetailsService) {
        
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
public String showLoginPage(@RequestParam(value = "error", required = false) String error, HttpServletResponse response) {
    if (error != null) {
        //had to load login as I was getting an error loggin in for some reason
        return "redirect:/login";
    }
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    return "login"; //just loads the login page
}


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (userDetails == null) {
                return "redirect:/register"; //aim was to send them to register but its not working properly
            }

            return "redirect:/home"; //send to home if you log in
        } catch (UsernameNotFoundException e) {


            System.out.println("Login failed for user: " + username);
            
            //need to clear the security as was interfering when I tried logging in sometimes.
            SecurityContextHolder.clearContext();
            
            model.addAttribute("error", "Invalid username or password");
            return "login"; //needs to pull up log in again if we run into error.
        }
    }
}





