package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders") //making sure that correct table is used
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //had trouble with this for so longggggg
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    
    @Column(nullable = false)
    private String status;
    
    private Double totalAmount;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User customer;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    
    public Long getId() {
        return id;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public String getStatus() {
        return status;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public User getCustomer() {
        return customer;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setCustomer(User customer) {
        this.customer = customer;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
