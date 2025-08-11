package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")//correct table
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Long getId() {
        return id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public Double getPrice() {
        return price;
    }
    public Product getProduct() {
        return product;
    }
    public Order getOrder() {
        return order;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}


