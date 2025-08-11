package com.example.demo.controller;

import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final ProductService productService;
    private final OrderService orderService;

    public AdminController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }
    // to /admin
    @PostMapping("/admin")
    public String updateAdmin(
            @RequestParam(value = "productId", required = false) Long productId,
            @RequestParam(value = "newStock", required = false) Integer newStock,
            @RequestParam(value = "hidden", required = false) Boolean hidden,
            @RequestParam(value = "orderId", required = false) Long orderId,
            @RequestParam(value = "newStatus", required = false) String newStatus) {
        if (productId != null) {
            if (newStock != null) {
                productService.updateStock(productId, newStock);
            }
            if (hidden != null) {
                productService.updateHidden(productId, hidden);
            }
        }
        if (orderId != null && newStatus != null) {
            orderService.updateOrderStatus(orderId, newStatus);
        }
        return "redirect:/admin";
    }

    //need endpoint to load admin page
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("orders", orderService.findAll());
        return "adminDashboard"; 
        //loads the html file
    }
    @PostMapping("/admin/addProduct")
public String addProduct(@RequestParam("name") String name,
                         @RequestParam("description") String description,
                         @RequestParam("price") Double price,
                         @RequestParam("stock") Integer stock,
                         @RequestParam(value = "imageUrl", required = false) String imageUrl,
                         @RequestParam(value = "hidden", required = false) Boolean hidden) {
    productService.addProduct(name, description, price, stock, imageUrl, hidden != null ? hidden : false);
    return "redirect:/admin";
}

@PostMapping("/admin/updateProduct") //updates product!!
public String updateProduct(@RequestParam("productId") Long productId,
                            @RequestParam("description") String description,
                            @RequestParam("price") Double price,
                            @RequestParam("stock") Integer stock,
                            @RequestParam("hidden") Boolean hidden) {
    productService.updateProduct(productId, description, price, stock, hidden);
    return "redirect:/admin";
}

@PostMapping("/admin/updateOrderStatus")//updates order status!!
public String updateOrderStatus(@RequestParam("orderId") Long orderId,
                                @RequestParam("newStatus") String newStatus) {
    orderService.updateOrderStatus(orderId, newStatus);
    return "redirect:/admin";
}

}

