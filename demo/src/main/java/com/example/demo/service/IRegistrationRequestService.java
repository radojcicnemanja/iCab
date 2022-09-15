package com.example.demo.service;

import com.example.demo.domain.Driver;
import com.example.demo.domain.RegistrationRequest;
import com.example.demo.domain.User;

import java.util.List;

public interface IRegistrationRequestService {
    List<Driver> getAllRegistrationRequests();
    RegistrationRequest getByUsername(String username);
    void createRegistrationRequest(String username);
    void deleteRegistrationRequest(Integer id);
    boolean approveRegistration(String username);
    void dispproveRegistration(String username);
}
