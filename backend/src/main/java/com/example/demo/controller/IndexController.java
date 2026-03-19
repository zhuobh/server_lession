package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> index() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "Demo API");
        info.put("version", "1.0.0");
        info.put("timestamp", LocalDateTime.now());
        info.put("endpoints", getEndpoints());
        return ResponseEntity.ok(ApiResponse.success(info));
    }

    private Map<String, String> getEndpoints() {
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("POST /api/users/register", "用户注册");
        endpoints.put("GET /api/users", "获取所有用户");
        endpoints.put("GET /api/users/{id}", "根据ID获取用户");
        endpoints.put("GET /api/users/username/{username}", "根据用户名获取用户");
        endpoints.put("PUT /api/users/{id}", "更新用户信息");
        endpoints.put("DELETE /api/users/{id}", "删除用户");
        endpoints.put("POST /api/products", "创建产品");
        endpoints.put("GET /api/products", "获取所有产品");
        endpoints.put("GET /api/products/{id}", "根据ID获取产品");
        endpoints.put("GET /api/products/category/{categoryName}", "根据分类获取产品");
        endpoints.put("GET /api/products/available", "获取可购买产品");
        endpoints.put("GET /api/products/search?keyword=xxx", "搜索产品");
        endpoints.put("PUT /api/products/{id}", "更新产品信息");
        endpoints.put("DELETE /api/products/{id}", "删除产品");
        return endpoints;
    }
}