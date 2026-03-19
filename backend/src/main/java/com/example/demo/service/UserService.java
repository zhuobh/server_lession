package com.example.demo.service;

import com.example.demo.dto.UserRegisterRequest;
import com.example.demo.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(UserRegisterRequest request);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    List<UserResponse> findAll();

    UserResponse update(Long id, UserRegisterRequest request);

    void delete(Long id);
}