package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {


        this.productRepository = productRepository;
    }

    public List<Product> findAll() {


        return productRepository.findAll();
    }

    @Transactional
    public void updateStock(Long productId, Integer newStock) {


        Product product = productRepository.findById(productId)
                           .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(newStock);
        productRepository.save(product);
    }

    @Transactional
    public void updateHidden(Long productId, Boolean hidden) {


        Product product = productRepository.findById(productId)
                           .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setHidden(hidden);
        productRepository.save(product);

    }
    @Transactional
public void addProduct(String name, String description, Double price, Integer stock, String imageUrl, Boolean hidden) {

    Product product = new Product();
    product.setName(name);
    product.setDescription(description);
    product.setPrice(price);
    product.setStock(stock);
    product.setImageUrl(imageUrl);
    product.setHidden(hidden);
    productRepository.save(product);

}

@Transactional
public void updateProduct(Long productId, String description, Double price, Integer stock, Boolean hidden) {


    Product product = productRepository.findById(productId)
                           .orElseThrow(() -> new RuntimeException("Product not found"));
    //just goes through all products to print
    product.setPrice(price);
    product.setStock(stock);
    product.setHidden(hidden);
    productRepository.save(product);

    
}

}

