package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.DriverDto;
import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.IRegistrationRequestService;
import com.example.demo.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRegistrationRequestService registrationRequestService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/")
    public String processRegister(@RequestBody CreateUserDto dto, HttpServletRequest request, UriComponentsBuilder ucBuilder)
            throws UnsupportedEncodingException, MessagingException {
        if(dto.getRole() == "Customer"){
            userService.createCustomer(dto, getSiteURL(request));
        }
        else{
            userService.createDriver(dto);
        }
        return "register_success";
    }

    @PostMapping("/approveRegistration")
    public HttpStatus approveRegistration(@RequestBody String username, HttpServletRequest request, UriComponentsBuilder ucBuilder)
    {
        if(registrationRequestService.approveRegistration(username)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/")
    public ResponseEntity<Collection<DriverDto>> getRegistrationRequests(){
        return new ResponseEntity<Collection<DriverDto>>(registrationRequestService.getAllRegistrationRequests()
                .stream()
                .map(request -> modelMapper.map(request, DriverDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @DeleteMapping("/{username}")
    public void dispproveRegistration(@PathVariable String username){
        registrationRequestService.dispproveRegistration(username);
    }

    @RequestMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        boolean verified = userService.verify(code);
        return verified ?
                "    <title>Verification succeeded!</title>\n" +
                        "    <div class=\"container text-center\">\n" +
                        "        <h3>Congratulations, your account has been verified.</h3>\n" +
                        "        <h4><a href=\"http://localhost:4200\">Click here to Login</a></h4>\n" +
                        "    </div>"
                :
                "    <title>Verification failed</title>\n" +
                        "    <div class=\"container text-center\">\n" +
                        "        <h3>Sorry, we could not verify account. It maybe already verified,\n" +
                        "            or verification code is incorrect.</h3>\n" +
                        "    </div>";
    }
}
