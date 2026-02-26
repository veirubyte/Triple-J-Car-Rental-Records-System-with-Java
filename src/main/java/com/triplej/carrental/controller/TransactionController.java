package com.triplej.carrental.controller;

import com.triplej.carrental.dto.*;
import com.triplej.carrental.model.*;
import com.triplej.carrental.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private CarRentalService service;

    @PostMapping("/rent")
    public ApiResponse rentCar(@RequestBody RentRequest request) {
        Rental rental = service.rentCar(request.getCustomerId(), request.getCarId());
        if (rental != null) {
            return ApiResponse.ok("Car rented successfully! Invoice generated.", rental);
        }
        return ApiResponse.error("Unable to rent. Car may not be available or customer not found.");
    }

    @PostMapping("/reserve")
    public ApiResponse reserveCar(@RequestBody ReserveRequest request) {
        Reservation reservation = service.reserveCar(request.getCustomerId(), request.getCarId());
        if (reservation != null) {
            return ApiResponse.ok("Car reserved successfully!", reservation);
        }
        return ApiResponse.error("Unable to reserve. Car not found or customer not found.");
    }

    @PostMapping("/return")
    public ApiResponse returnCar(@RequestBody ReturnRequest request) {
        String result = service.returnCar(request.getCustomerId(), request.getCarId());
        if (result.contains("successfully") || result.contains("available")) {
            return ApiResponse.ok(result);
        }
        return ApiResponse.error(result);
    }

    @GetMapping("/profile/{customerId}")
    public ApiResponse getProfile(@PathVariable int customerId) {
        Map<String, Object> profile = service.getCustomerProfile(customerId);
        if (profile != null) {
            return ApiResponse.ok("Profile retrieved.", profile);
        }
        return ApiResponse.error("Customer not found.");
    }
}
