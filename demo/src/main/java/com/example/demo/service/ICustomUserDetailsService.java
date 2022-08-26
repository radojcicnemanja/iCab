package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface ICustomUserDetailsService {
    UserDetails loadUserByUsername(String username);
}
