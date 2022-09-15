package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Driver extends User{

    @Column(name = "car_description", length = 100)
    private String carDescription = "";

    public Driver() {}

    public Driver(String name, String lastName, String username, String password, String email,
                  String phoneNumber, String carDescription) {
        super(name, lastName, username, password, email, phoneNumber);
        this.carDescription = carDescription;
    }
}
