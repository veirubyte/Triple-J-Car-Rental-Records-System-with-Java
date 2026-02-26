package com.triplej.carrental.service;

import com.triplej.carrental.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarRentalService {

    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<ReturnRecord> returnRecords = new ArrayList<>();
    private int nextCustomerID = 1;
    private int nextRentalID = 1;
    private int nextReservationID = 1;
    private int nextInvoiceID = 1;
    private int nextReturnID = 1;
    private Employee defaultEmployee;

    @PostConstruct
    public void init() {
        defaultEmployee = new Employee(1, "Triple-J Employee", "Staff");
        employees.add(defaultEmployee);

        cars.add(new Car(111, "Toyota", "Vios", "White", 1500.0, "AVAILABLE"));
        cars.add(new Car(112, "Honda", "City", "Black", 1800.0, "AVAILABLE"));
        cars.add(new Car(113, "Mitsubishi", "Mirage", "Red", 1300.0, "AVAILABLE"));
        cars.add(new Car(114, "Honda", "Civic", "Black", 1300.0, "AVAILABLE"));
        cars.add(new Car(115, "Ford", "Ranger", "Blue", 1300.0, "AVAILABLE"));
        cars.add(new Car(116, "Nissan", "Almera", "Gray", 1300.0, "AVAILABLE"));
        cars.add(new Car(117, "Mitsubishi", "Mirage", "Red", 1300.0, "AVAILABLE"));
        cars.add(new Car(118, "Chevrolet", "Trailblazer", "Dark Green", 1300.0, "AVAILABLE"));
        cars.add(new Car(119, "Kia", "Sportage", "Red", 2200.0, "AVAILABLE"));
        cars.add(new Car(120, "Hyundai", "Tucson", "Silver", 2300.0, "AVAILABLE"));
        cars.add(new Car(121, "Mazda", "CX-5", "Black", 2700.0, "AVAILABLE"));
        cars.add(new Car(122, "Toyota", "Corolla", "White", 1900.0, "AVAILABLE"));
        cars.add(new Car(123, "Honda", "CR-V", "Blue", 2600.0, "AVAILABLE"));
        cars.add(new Car(124, "Ford", "Everest", "Gray", 3200.0, "AVAILABLE"));
        cars.add(new Car(125, "Nissan", "X-Trail", "Silver", 2400.0, "AVAILABLE"));
        cars.add(new Car(126, "Nissan", "Altima", "Black", 2100.0, "AVAILABLE"));
        cars.add(new Car(127, "Ford", "Mustang", "Red", 3500.0, "AVAILABLE"));
        cars.add(new Car(128, "Toyota", "Fortuner", "White", 3000.0, "AVAILABLE"));
        cars.add(new Car(129, "Honda", "Accord", "Silver", 2200.0, "AVAILABLE"));
        cars.add(new Car(130, "Nissan", "Navara", "Blue", 2800.0, "AVAILABLE"));
    }

    // --- Car operations ---

    public List<Car> getAllCars() {
        return cars;
    }

    public List<Car> getAvailableCars() {
        return cars.stream()
                .filter(Car::isAvailable)
                .collect(Collectors.toList());
    }

    public Car findCarById(int id) {
        return cars.stream()
                .filter(c -> c.getCarID() == id)
                .findFirst()
                .orElse(null);
    }

    // --- Customer / Auth operations ---

    public Customer login(int customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerID() == customerId)
                .findFirst()
                .orElse(null);
    }

    public Customer register(String name, String contactNumber) {
        int id = nextCustomerID++;
        Customer customer = new Customer(id, name, contactNumber);
        customers.add(customer);
        return customer;
    }

    // --- Transaction operations ---

    public Rental rentCar(int customerId, int carId) {
        Customer customer = login(customerId);
        if (customer == null) return null;

        Car car = findCarById(carId);
        if (car == null || !car.isAvailable()) return null;

        Rental rental = new Rental(nextRentalID++, car, customer, defaultEmployee);
        customer.addRental(rental);

        // Generate invoice
        Invoice invoice = new Invoice(nextInvoiceID++, customer, rental, car.getPrice());
        customer.addInvoice(invoice);

        return rental;
    }

    public Reservation reserveCar(int customerId, int carId) {
        Customer customer = login(customerId);
        if (customer == null) return null;

        Car car = findCarById(carId);
        if (car == null) return null;

        Reservation reservation = new Reservation(nextReservationID++, car, customer);
        car.setStatus("RESERVED");
        customer.addReservation(reservation);
        return reservation;
    }

    public String returnCar(int customerId, int carId) {
        Customer customer = login(customerId);
        if (customer == null) return "Customer not found.";

        // Check rentals first
        for (Rental r : customer.getRentals()) {
            if (r.getCar().getCarID() == carId && r.isActive()) {
                ReturnRecord ret = new ReturnRecord(nextReturnID++, r);
                returnRecords.add(ret);
                return "Rented car returned successfully!";
            }
        }

        // Check reservations
        for (Reservation res : customer.getReservations()) {
            if (res.getCar().getCarID() == carId && res.getStatus().equalsIgnoreCase("RESERVED")) {
                res.setStatus("CANCELLED");
                res.getCar().makeAvailable();
                return "Reserved car has been returned and is now available!";
            }
        }

        return "No active rental or reservation found for that Car ID.";
    }

    // --- Profile / History ---

    public Map<String, Object> getCustomerProfile(int customerId) {
        Customer customer = login(customerId);
        if (customer == null) return null;

        Map<String, Object> profile = new LinkedHashMap<>();
        profile.put("customer", customer);
        profile.put("rentals", customer.getRentals());
        profile.put("reservations", customer.getReservations());
        profile.put("invoices", customer.getInvoices());
        return profile;
    }

    // --- Stats ---

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalCars", cars.size());
        stats.put("availableCars", cars.stream().filter(Car::isAvailable).count());
        stats.put("rentedCars", cars.stream().filter(c -> c.getStatus().equalsIgnoreCase("RENTED")).count());
        stats.put("reservedCars", cars.stream().filter(c -> c.getStatus().equalsIgnoreCase("RESERVED")).count());
        stats.put("totalCustomers", customers.size());
        return stats;
    }
}
