package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ProductResponse findById(Long id);

    List<ProductResponse> findAll();

    List<ProductResponse> findByCategory(String categoryName);

    List<ProductResponse> findAvailable();

    List<ProductResponse> search(String keyword);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}