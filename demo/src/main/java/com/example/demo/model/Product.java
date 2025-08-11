package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products") //correct table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;

    @Column(name = "image_url")
    private String imageUrl;

   
    @Column(name = "hidden")
    private Boolean hidden = false;  //default is false as we naturally want to show our products.

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getStock() {
        return stock;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public Boolean getHidden() {
        return hidden;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
}
