package com.triplej.carrental.dto;

public class RentRequest {
    private int customerId;
    private int carId;

    public RentRequest() {}

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }
}
