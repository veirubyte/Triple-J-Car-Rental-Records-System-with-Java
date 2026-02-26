package com.triplej.carrental.model;

public class Employee {
    private int employeeID;
    private String name;
    private String role;

    public Employee() {}

    public Employee(int employeeID, String name, String role) {
        this.employeeID = employeeID;
        this.name = name;
        this.role = role;
    }

    public int getEmployeeID() { return employeeID; }
    public String getName() { return name; }
    public String getRole() { return role; }

    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
}
