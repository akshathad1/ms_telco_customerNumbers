package com.customer.data.service;

import com.customer.data.exception.CustomerServiceException;
import com.customer.data.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@AllArgsConstructor
@Service
@ComponentScan
public class CustomerDataService {

    @Autowired
    private CustomerRepository customerRepository;

    public ArrayList<String> getPhoneNumber(String customerId) throws Exception {
        return customerRepository.getCustomerPhoneNumber(customerId);
    }

    public ArrayList<String> getAllPhoneNumbers() {
        return customerRepository.getAllCustomerPhoneNumber();
    }

    public void activatePhoneNumber(String phoneNumber) throws CustomerServiceException {
         customerRepository.activateNumber(phoneNumber);

    }
}
