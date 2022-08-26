package com.example.demo.controller;

import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @PostMapping("/")
    public String processRegister(@RequestBody CreateUserDto dto, HttpServletRequest request, UriComponentsBuilder ucBuilder)
            throws UnsupportedEncodingException, MessagingException {
        userService.createUser(dto, getSiteURL(request));
        return "register_success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
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
