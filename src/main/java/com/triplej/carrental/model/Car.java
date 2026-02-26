package com.triplej.carrental.model;

public class Car {
    private int carID;
    private String brand;
    private String model;
    private String color;
    private double price;
    private String status;

    public Car() {}

    public Car(int carID, String brand, String model, String color, double price, String status) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
        this.status = status;
    }

    public int getCarID() { return carID; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setCarID(int carID) { this.carID = carID; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }

    public void rent() { this.status = "RENTED"; }
    public void reserve() { this.status = "RESERVED"; }
    public void makeAvailable() { this.status = "AVAILABLE"; }
    public boolean isAvailable() { return status.equalsIgnoreCase("AVAILABLE"); }
}
