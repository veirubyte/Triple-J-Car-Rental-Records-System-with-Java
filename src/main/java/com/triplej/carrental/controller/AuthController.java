package com.triplej.carrental.controller;

import com.triplej.carrental.dto.*;
import com.triplej.carrental.model.Customer;
import com.triplej.carrental.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CarRentalService service;

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        Customer customer = service.login(request.getCustomerId());
        if (customer != null) {
            return ApiResponse.ok("Login successful! Welcome, " + customer.getName(), customer);
        }
        return ApiResponse.error("Customer not found.");
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            return ApiResponse.error("Name is required.");
        }
        Customer customer = service.register(request.getName(), request.getContactNumber());
        return ApiResponse.ok("Registration successful! Your Customer ID is " + customer.getCustomerID(), customer);
    }
}
