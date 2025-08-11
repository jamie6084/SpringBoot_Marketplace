package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // need endpoint to show shop
    @GetMapping("/shop")
    public String showShopPage(Model model) {

        
        // need to get all products in the database
        List<Product> products = productRepository.findAll();
        // need to add all prods to it
        model.addAttribute("products", products);
        // load the almighty shop
        return "shop";
    }

   
}

