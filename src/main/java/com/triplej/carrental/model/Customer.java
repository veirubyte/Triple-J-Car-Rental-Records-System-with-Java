package com.triplej.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String name;
    private String contactNumber;

    @JsonIgnore
    private List<Rental> rentals;
    @JsonIgnore
    private List<Reservation> reservations;
    @JsonIgnore
    private List<Invoice> invoices;

    public Customer() {}

    public Customer(int customerID, String name, String contactNumber) {
        this.customerID = customerID;
        this.name = name;
        this.contactNumber = contactNumber;
        this.rentals = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }

    @JsonIgnore
    public List<Rental> getRentals() { return rentals; }
    @JsonIgnore
    public List<Reservation> getReservations() { return reservations; }
    @JsonIgnore
    public List<Invoice> getInvoices() { return invoices; }

    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public void addRental(Rental rental) { rentals.add(rental); }
    public void addReservation(Reservation reservation) { reservations.add(reservation); }
    public void addInvoice(Invoice invoice) { invoices.add(invoice); }
}
