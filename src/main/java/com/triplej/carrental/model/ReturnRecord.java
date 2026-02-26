package com.triplej.carrental.model;

import java.time.LocalDate;

public class ReturnRecord {
    private int returnID;
    private int rentalID;
    private int carID;
    private int customerID;
    private LocalDate returnDate;
    private String remarks;
    private String rentalStatus;

    public ReturnRecord() {}

    public ReturnRecord(int returnID, Rental rental) {
        this.returnID = returnID;
        this.rentalID = rental.getRentalID();
        this.carID = rental.getCar().getCarID();
        this.customerID = rental.getCustomerID();
        this.returnDate = LocalDate.now();
        this.remarks = "";

        if (rental.isActive()) {
            rental.returnCar();
        }
        this.rentalStatus = rental.getStatus();
    }

    public int getReturnID() { return returnID; }
    public int getRentalID() { return rentalID; }
    public int getCarID() { return carID; }
    public int getCustomerID() { return customerID; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getRemarks() { return remarks; }
    public String getRentalStatus() { return rentalStatus; }

    public void setRemarks(String remarks) { this.remarks = remarks; }
}
