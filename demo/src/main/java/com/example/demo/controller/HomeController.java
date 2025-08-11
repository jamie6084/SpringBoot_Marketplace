package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
        }

        List<Product> products = productRepository.findAll();
        
        //list fr the products for home page, I just want last 3
        List<Product> featuredProducts;
        if (products.size() > 3) {
            
            featuredProducts = products.subList(products.size() - 3, products.size()); // gets the last 3 rn
        } else {

            featuredProducts = products;
        }

        
       // Collections.shuffle(featuredProducts);
       //was gonna use this to shuffle it but not adding

        model.addAttribute("featuredProducts", featuredProducts);
        return "home"; //home page!!!!!
    }
}


