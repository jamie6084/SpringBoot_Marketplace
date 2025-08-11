package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //needs to make new order w the order date and stuff
    @Transactional
    public Order createOrder(User customer, List<OrderItem> orderItems, LocalDateTime orderDate) {
        Order order = new Order();
        order.setCustomer(customer);
        
        
        order.setOrderDate(orderDate != null ? orderDate : LocalDateTime.now());

        order.setStatus("PENDING");

        double total = orderItems.stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity())
                        .sum();
        order.setTotalAmount(total);

        
        orderItems.forEach(item -> item.setOrder(order));
        order.setOrderItems(orderItems);
        
        return orderRepository.save(order);
    }

    
    @Transactional
    public Order updateOrderDate(Long orderId, LocalDateTime newOrderDate) {
        Order order = orderRepository.findById(orderId)
                          .orElseThrow(() -> new RuntimeException("Order not found"));
        
        order.setOrderDate(newOrderDate);
        return orderRepository.save(order);
    }

    //needs to get all orders from a certain someone.
    public List<Order> getOrdersForCustomer(User customer) {
        return orderRepository.findByCustomer(customer);
    }

    //this just gets all total orders from everyone.
    public List<Order> findAll() {

        
        return orderRepository.findAll();
    }
    
    //here we update the status of an order
    public Order updateOrderStatus(Long orderId, String newStatus) {


        Order order = orderRepository.findById(orderId)
                          .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}
