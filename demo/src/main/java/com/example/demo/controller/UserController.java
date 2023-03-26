package com.example.demo.controller;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.DriverDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.IUserService;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUserService userService;

    @GetMapping("/")
    public ResponseEntity<Collection<UserDto>> getUsers(){
        return new ResponseEntity<Collection<UserDto>>(userService.getAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getDrivers")
    public ResponseEntity<Collection<DriverDto>> getDrivers(){
        return new ResponseEntity<Collection<DriverDto>>(userService.getAllDrivers()
                .stream()
                .map(driver -> modelMapper.map(driver, DriverDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username){
        return new ResponseEntity<UserDto>(modelMapper.map(userService.getByUsername(username), UserDto.class), HttpStatus.OK);

    }

//    @PostMapping("/")
//    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto dto){
//        return new ResponseEntity<UserDto>(modelMapper.map(userService.createUser(dto), UserDto.class), HttpStatus.OK);
//    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public  ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UpdateUserDto dto){
        return new ResponseEntity<UserDto>(modelMapper.map(userService.updateUser(userId, dto), UserDto.class), HttpStatus.OK);
    }


}
