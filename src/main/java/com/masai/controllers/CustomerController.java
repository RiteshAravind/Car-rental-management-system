package com.masai.controllers;

import com.masai.models.Customer;
import com.masai.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) throws IOException {
        String message = customerService.save(customer);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) throws Exception {
        String message = customerService.customerLogin(customer);
        return ResponseEntity.ok(message);
    }
}
