package com.example.demo.domain;

import javax.persistence.*;

@Entity
public class RegistrationRequest {
    @Id
    @SequenceGenerator(name = "requestIdSeqGen", sequenceName = "requestIdSeq", initialValue = 14, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestIdSeqGen")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    public RegistrationRequest(){}

    public RegistrationRequest(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
