package com.example.demo.service;

import com.example.demo.domain.Role;

import java.util.List;

public interface IRoleService {
    Role findById(Long id);
    List<Role> findByName(String name);
}

