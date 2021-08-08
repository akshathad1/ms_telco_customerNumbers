package com.customer.data.controller;

import com.customer.data.exception.CustomerServiceException;
import com.customer.data.service.CustomerDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
public class CustomerController {

    private static final String SUCCESS_MESSAGE = "Activation Successfully complete";
    @Autowired
    CustomerDataService customerService;

    @GetMapping("/customer/{customerId}/phoneNumbers")
    public ResponseEntity<ArrayList<String>> getCustomerPhoneNumbers(@PathVariable String customerId) throws Exception {
        try {
            ArrayList<String> result = customerService.getPhoneNumber(customerId);
            return ResponseEntity.ok().body(result);
        } catch (CustomerServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/customer/phoneNumbers")
    ResponseEntity<ArrayList<String>> getAllCustomerNumbers() {
        return ResponseEntity.ok().body(customerService.getAllPhoneNumbers());
    }

    @PutMapping("/activate/{phoneNumber}")
    ResponseEntity<String> activatePhoneNumber(@PathVariable(value = "phoneNumber", required = true) String phoneNumber) throws CustomerServiceException {
        try {
            customerService.activatePhoneNumber(phoneNumber);
            return ResponseEntity.ok().body(SUCCESS_MESSAGE);
        } catch (CustomerServiceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
