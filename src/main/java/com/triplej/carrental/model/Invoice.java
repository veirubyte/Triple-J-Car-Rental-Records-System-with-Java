package com.triplej.carrental.model;

import java.time.LocalDate;

public class Invoice {
    private int invoiceID;
    private int customerID;
    private String customerName;
    private int rentalID;
    private int carID;
    private String carBrand;
    private String carModel;
    private String employeeName;
    private double amount;
    private LocalDate invoiceDate;

    public Invoice() {}

    public Invoice(int invoiceID, Customer customer, Rental rental, double amount) {
        this.invoiceID = invoiceID;
        this.customerID = customer.getCustomerID();
        this.customerName = customer.getName();
        this.rentalID = rental.getRentalID();
        this.carID = rental.getCar().getCarID();
        this.carBrand = rental.getCar().getBrand();
        this.carModel = rental.getCar().getModel();
        this.employeeName = rental.getEmployee().getName();
        this.amount = amount;
        this.invoiceDate = LocalDate.now();
    }

    public int getInvoiceID() { return invoiceID; }
    public int getCustomerID() { return customerID; }
    public String getCustomerName() { return customerName; }
    public int getRentalID() { return rentalID; }
    public int getCarID() { return carID; }
    public String getCarBrand() { return carBrand; }
    public String getCarModel() { return carModel; }
    public String getEmployeeName() { return employeeName; }
    public double getAmount() { return amount; }
    public LocalDate getInvoiceDate() { return invoiceDate; }
}
