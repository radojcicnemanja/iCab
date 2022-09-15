package com.example.demo.service.impl;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Driver;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findAll();
        List<Driver> drivers = driverRepository.findAll();
        for(Customer user: customers){
            if(username.equals(user.getUsername()))
                return (UserDetails) user;
        }
        for(Driver user: drivers){
            if(username.equals(user.getUsername()))
                return (UserDetails) user;
        }
        return null;
    }

}

