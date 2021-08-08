
package com.customer.data.repository;

import com.customer.data.application.model.Customer;
import com.customer.data.application.model.PhoneNumberDetails;
import com.customer.data.exception.CustomerServiceException;
import com.customer.data.repository.provider.CustomerDetailsRepositoryProvider;
import com.customer.data.repository.provider.PhoneNumberDetailsRepositoryProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@ComponentScan
@Slf4j
public class CustomerRepository {

  @Autowired
  CustomerDetailsRepositoryProvider customerRepositoryProvider;

  @Autowired
  PhoneNumberDetailsRepositoryProvider phoneNumberDetailsRepositoryProvider;

  public ArrayList<String> getCustomerPhoneNumber(String customerId) throws CustomerServiceException {

    Optional<Customer> customer = customerRepositoryProvider.findById(customerId);

    if (customer.isPresent()) {
      log.info("Valid customer ID, phone number found");
      Set<PhoneNumberDetails> set = customer.get().getPhoneNumbers();
      ArrayList<String> phoneNumbersList = new ArrayList<>();
      set.stream().map(s -> phoneNumbersList.add(s.getCustomerPhoneNumber()))
          .collect(Collectors.toList());
      return phoneNumbersList;
    }
    log.info("Invalid customer ID entered by user");
    throw new CustomerServiceException("Customer ID not found");
  }

  public ArrayList<String> getAllCustomerPhoneNumber() {
    Iterable<PhoneNumberDetails> num = phoneNumberDetailsRepositoryProvider.findAll();
    log.info("consolidating list of phone numbers");
    List<String> numDetails = StreamSupport
        .stream(num.spliterator(), false)
        .map(iter -> iter.getCustomerPhoneNumber())
        .collect(Collectors.toList());

    return (ArrayList<String>) numDetails;

  }


  public void activateNumber(String phoneNumber) throws CustomerServiceException {
    Optional<PhoneNumberDetails> phoneDetails = phoneNumberDetailsRepositoryProvider.findById(phoneNumber);
    if (phoneDetails.isPresent()) {
      log.info("Valid phone number,activating number");
      phoneDetails.get().setActivationStatus("Y");
      phoneNumberDetailsRepositoryProvider.save(phoneDetails.get());
      log.info("Phone number successfully validated");
    } else {
      log.info("Phone number not found , cannot be activated");
      throw new CustomerServiceException("Phone number not found");
    }

  }
}