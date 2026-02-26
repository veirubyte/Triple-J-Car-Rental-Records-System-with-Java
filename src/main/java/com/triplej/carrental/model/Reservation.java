package com.triplej.carrental.model;

import java.time.LocalDate;

public class Reservation {
    private int reservationID;
    private Car car;
    private Customer customer;
    private LocalDate reservationDate;
    private String status;

    public Reservation() {}

    public Reservation(int reservationID, Car car, Customer customer) {
        this.reservationID = reservationID;
        this.car = car;
        this.customer = customer;
        this.reservationDate = LocalDate.now();
        this.status = "RESERVED";
    }

    public int getReservationID() { return reservationID; }
    public Car getCar() { return car; }
    public int getCarID() { return car != null ? car.getCarID() : 0; }
    public String getCarBrand() { return car != null ? car.getBrand() : ""; }
    public String getCarModel() { return car != null ? car.getModel() : ""; }
    public int getCustomerID() { return customer != null ? customer.getCustomerID() : 0; }
    public String getCustomerName() { return customer != null ? customer.getName() : ""; }
    public LocalDate getReservationDate() { return reservationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
