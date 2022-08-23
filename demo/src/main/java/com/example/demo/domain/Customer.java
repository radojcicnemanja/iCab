package com.example.demo.domain;

import javax.persistence.Entity;

@Entity
public class Customer extends User {
    public Customer() {}

    public Customer(String name, String lastName, String email, String phoneNumber) {
        super(name, lastName, email, phoneNumber);
    }
}
