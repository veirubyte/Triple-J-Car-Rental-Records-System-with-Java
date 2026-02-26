package com.triplej.carrental.dto;

public class ReturnRequest {
    private int customerId;
    private int carId;

    public ReturnRequest() {}

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }
}
