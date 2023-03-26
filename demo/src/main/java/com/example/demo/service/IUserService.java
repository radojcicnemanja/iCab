package com.example.demo.service;

import com.example.demo.domain.Driver;
import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;

import java.util.Collection;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User createCustomer(CreateUserDto dto, String url);

    User createDriver(CreateUserDto dto);

    void deleteUser(Integer userId);

    User updateUser(Integer userId, UpdateUserDto dto);

    boolean verify(String code);

    User getByUsername(String username);

    Collection<Driver> getAllDrivers();
}
