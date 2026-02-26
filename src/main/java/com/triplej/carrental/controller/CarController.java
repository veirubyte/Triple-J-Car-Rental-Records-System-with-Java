package com.triplej.carrental.controller;

import com.triplej.carrental.dto.ApiResponse;
import com.triplej.carrental.model.Car;
import com.triplej.carrental.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRentalService service;

    @GetMapping
    public ApiResponse getAllCars() {
        List<Car> cars = service.getAllCars();
        return ApiResponse.ok("All cars retrieved.", cars);
    }

    @GetMapping("/available")
    public ApiResponse getAvailableCars() {
        List<Car> cars = service.getAvailableCars();
        return ApiResponse.ok("Available cars retrieved.", cars);
    }

    @GetMapping("/{id}")
    public ApiResponse getCarById(@PathVariable int id) {
        Car car = service.findCarById(id);
        if (car != null) {
            return ApiResponse.ok("Car found.", car);
        }
        return ApiResponse.error("Car not found.");
    }

    @GetMapping("/stats")
    public ApiResponse getStats() {
        Map<String, Object> stats = service.getStats();
        return ApiResponse.ok("Stats retrieved.", stats);
    }
}
