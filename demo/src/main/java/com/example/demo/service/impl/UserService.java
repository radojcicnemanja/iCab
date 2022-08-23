package com.example.demo.service.impl;

import com.example.demo.domain.Customer;
import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(CreateUserDto dto) {
        Customer newCustomer = new Customer(dto.getName(), dto.getLastName(), dto.getEmail(), dto.getPhoneNumber());
        return customerRepository.save(newCustomer);
    }

    @Override
    public void deleteUser(Integer userId) {
        customerRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Integer userId, UpdateUserDto dto) {
        Customer customer = customerRepository.findById(userId).orElse(null);
        customer.setName(dto.getName());
        customer.setLastName(dto.getLastName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        customerRepository.save(customer);

        return customerRepository.findById(userId).orElse(null);
    }
}
