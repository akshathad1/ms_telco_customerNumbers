package com.customer.data.controller;


import com.customer.data.exception.CustomerServiceException;
import com.customer.data.service.CustomerDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerControllerTest {
  @Mock
  private CustomerDataService customerDataService;


  @InjectMocks
  private CustomerController customerController;

  @Test
  public void activateNonexistantPhoneNumbertest_Should_Throw_Exception() throws CustomerServiceException {
    doThrow(new CustomerServiceException("not found")).when(customerDataService).activatePhoneNumber(any());
    ResponseEntity<String> result = customerController.activatePhoneNumber("667788");
    Assertions.assertTrue(result.toString().contains("404 NOT_FOUND"));
  }


  @Test
  public void activateValidPhoneNumbertest() throws CustomerServiceException {
    ResponseEntity<String> result = customerController.activatePhoneNumber("667788");
    Assertions.assertTrue(result.toString().contains("200 OK"));
  }


  @Test
  public void getNonexistantPhoneNumbertest_should_throwException() throws Exception {
    doThrow(new CustomerServiceException("not found")).when(customerDataService).getPhoneNumber(any());
    ResponseEntity<ArrayList<String>> result = customerController.getCustomerPhoneNumbers("667788");
    Assertions.assertTrue(result.toString().contains("404 NOT_FOUND"));
  }


  @Test
  public void getValidPhoneNumbertest() throws Exception {

    ResponseEntity<ArrayList<String>> result = customerController.getCustomerPhoneNumbers("667788");
    Assertions.assertTrue(result.toString().contains("200 OK"));
  }


  @Test
  public void ValidTest_getAllPhoneNumbertest() throws Exception {

    ArrayList<String> mockList = new ArrayList<>();
    mockList.add("789065444");
    mockList.add("789888944");
    when(customerDataService.getAllPhoneNumbers()).thenReturn(mockList);
    ResponseEntity<ArrayList<String>> result = customerController.getAllCustomerNumbers();
    Assertions.assertTrue(result.toString().contains("200 OK"));
    System.out.println("re" + result.getBody().size());
    Assertions.assertTrue(result.getBody().size() > 0);
  }

}


