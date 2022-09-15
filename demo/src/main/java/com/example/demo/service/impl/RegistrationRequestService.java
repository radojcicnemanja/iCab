package com.example.demo.service.impl;

import com.example.demo.domain.Driver;
import com.example.demo.domain.RegistrationRequest;
import com.example.demo.domain.User;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IRegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationRequestService implements IRegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllRegistrationRequests() {
        List<Driver> drivers = driverRepository.findAll();
        List<RegistrationRequest> registrationRequests = registrationRequestRepository.findAll();
        List<Driver> unregistered_drivers = new ArrayList<>();

        for (RegistrationRequest request: registrationRequests) {
            for (Driver driver: drivers) {
                if(request.getUsername().equals(driver.getUsername())){
                    unregistered_drivers.add(driver);
                }
            }
        }
        return unregistered_drivers;
    }

    @Override
    public RegistrationRequest getByUsername(String username) {
        return registrationRequestRepository.findByUsername(username);
    }

    @Override
    public void createRegistrationRequest(String username) {
        registrationRequestRepository.save(new RegistrationRequest(username));
    }

    @Override
    public void deleteRegistrationRequest(Integer id) {
        registrationRequestRepository.deleteById(id);
    }

    @Override
    public boolean approveRegistration(String username) {
        try {
            RegistrationRequest request = registrationRequestRepository.findByUsername(username);
            registrationRequestRepository.deleteById(request.getId());
            Driver driver = driverRepository.findByUsername(username);
            driver.setEnabled(true);
            driverRepository.save(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void dispproveRegistration(String username) {
        try {
            RegistrationRequest request = registrationRequestRepository.findByUsername(username);
            registrationRequestRepository.deleteById(request.getId());
            Driver driver = driverRepository.findByUsername(username);
            driverRepository.deleteById(driver.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
