package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public CartController(ProductRepository productRepository, UserRepository userRepository, OrderService orderService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        List<CartItem> cart = getCart(session);
        double totalAmount = cart.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("cartItems", cart);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }
    //shows cartt

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<CartItem> cart = getCart(session);
        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            CartItem newItem = new CartItem(productId, product.getName(), product.getPrice(), quantity);
            cart.add(newItem);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCartItem(@RequestParam Long productId, 
                                 @RequestParam int quantity, 
                                 HttpSession session) {
        List<CartItem> cart = getCart(session);
        if (cart != null) {
            for (CartItem item : cart) {
                if (item.getProductId().equals(productId)) {
                    item.setQuantity(quantity);
                    break;
                }
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId, HttpSession session) {
        List<CartItem> cart = getCart(session);
        cart.removeIf(item -> item.getProductId().equals(productId));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // to checkout or later changed to just place order.
    @PostMapping("/checkout")
    public String checkout(@RequestParam(required = false) String orderDate, 
                           HttpSession session, 
                           Authentication authentication) {
        List<CartItem> cart = getCart(session);
        if (cart.isEmpty()) {
            return "redirect:/cart?empty=true";
        }



        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found for id: " + cartItem.getProductId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }

        LocalDateTime parsedOrderDate = null;
        if (orderDate != null && !orderDate.isEmpty()) {
            try {
                
                parsedOrderDate = LocalDateTime.parse(orderDate, dateTimeFormatter);
            } catch (Exception e) {
                return "redirect:/cart?error=invalid_date";
            }
        }

        Order order = orderService.createOrder(user, orderItems, parsedOrderDate);
        session.removeAttribute("cart");
        return "redirect:/orderConfirmation?orderId=" + order.getId();
    }
}


