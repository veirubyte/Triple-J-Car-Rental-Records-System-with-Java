package com.triplej.carrental.model;

import java.time.LocalDate;

public class Rental {
    private int rentalID;
    private Car car;
    private Customer customer;
    private Employee employee;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private String status;

    public Rental() {}

    public Rental(int rentalID, Car car, Customer customer, Employee employee) {
        this.rentalID = rentalID;
        this.car = car;
        this.customer = customer;
        this.employee = employee;
        this.rentalDate = LocalDate.now();

        if (car.isAvailable()) {
            car.rent();
            this.status = "ACTIVE";
        } else {
            this.status = "CANCELLED";
        }
    }

    public int getRentalID() { return rentalID; }
    public Car getCar() { return car; }
    public int getCarID() { return car != null ? car.getCarID() : 0; }
    public String getCarBrand() { return car != null ? car.getBrand() : ""; }
    public String getCarModel() { return car != null ? car.getModel() : ""; }
    public String getCarColor() { return car != null ? car.getColor() : ""; }
    public double getCarPrice() { return car != null ? car.getPrice() : 0; }
    public int getCustomerID() { return customer != null ? customer.getCustomerID() : 0; }
    public String getCustomerName() { return customer != null ? customer.getName() : ""; }
    public Employee getEmployee() { return employee; }
    public String getEmployeeName() { return employee != null ? employee.getName() : ""; }
    public LocalDate getRentalDate() { return rentalDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    public void returnCar() {
        if (status.equalsIgnoreCase("ACTIVE")) {
            this.status = "RETURNED";
            this.returnDate = LocalDate.now();
            car.makeAvailable();
        }
    }

    public boolean isActive() { return status.equalsIgnoreCase("ACTIVE"); }
}
