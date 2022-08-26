package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends User {

    @Column(name = "verification_code", length = 64)
    private String verificationCode = "";

    public Customer() {}

    public Customer(String name, String lastName, String username, String password, String email, String phoneNumber, String verificationCode) {
        super(name, lastName, username, password, email, phoneNumber);
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
