package com.example.demo.service.impl;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Driver;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.UpdateUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IRegistrationRequestService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRegistrationRequestService registrationRequestService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createCustomer(CreateUserDto dto, String siteURL) {
        String randomCode = RandomString.make(64);
        Customer customer = new Customer(dto.getName(), dto.getLastName(), dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getEmail(), dto.getPhoneNumber(), dto.getVerificationCode());
        customer.setVerificationCode(randomCode);

        List<Role> roles = roleService.findByName("ROLE_CUSTOMER");
        customer.setRoles(roles);

        customerRepository.save(customer);

        try {
            sendVerificationEmail(customer, siteURL);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return customerRepository.save(customer);
    }

    @Override
    public User createDriver(CreateUserDto dto) {
        String randomCode = RandomString.make(64);
        Driver driver = new Driver(dto.getName(), dto.getLastName(), dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getEmail(), dto.getPhoneNumber(), dto.getCarDescription());

        List<Role> roles = roleService.findByName("ROLE_DRIVER");
        driver.setRoles(roles);

        registrationRequestService.createRegistrationRequest(driver.getUsername());

        return driverRepository.save(driver);
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

    public void sendVerificationEmail(Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = customer.getEmail();
        String fromAddress = "isatestmail2021@gmail.com";
        String senderName = "ISA project";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", customer.getName());
        String verifyURL = siteURL + "/api/registration" + "/verify?code=" + customer.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    public boolean verify(String verificationCode) {
        Customer customer = customerRepository.findByVerificationCode(verificationCode);

        if (customer == null || customer.isEnabled()) {
            return false;
        } else {
            customer.setVerificationCode(null);
            customer.setEnabled(true);
            customerRepository.save(customer);

            return true;
        }

    }

    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Collection<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}
