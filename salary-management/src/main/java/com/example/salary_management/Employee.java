package com.example.salarymanagement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    private Long id;
    private String name;
    private int age;
    private double salary;

    // Constructors, getters and setters
    public Employee() {}

    public Employee(Long id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Getters and Setters
}
