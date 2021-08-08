package com.customer.data.service;

import com.customer.data.exception.CustomerServiceException;
import com.customer.data.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class CustomerServiceTest {

  @InjectMocks
  CustomerDataService customerDataService;

  @Mock
  private CustomerRepository customerRepository;

  @Test
  public void activateNonexistantPhoneNumbertest_Should_Throw_Exception_CustomerService() throws CustomerServiceException {
    doThrow(new CustomerServiceException("not found")).when(customerRepository).activateNumber(any());
    Exception ex = assertThrows(CustomerServiceException.class, () -> customerDataService.activatePhoneNumber(any()));

  }

  @Test
  public void activateValidPhoneNumbertest_CustomerService() throws CustomerServiceException {
    assertDoesNotThrow(() -> customerDataService.activatePhoneNumber(any()));
  }

  @Test
  public void getNonexistantPhoneNumbertest_should_throwException_CustomerService() throws Exception {
    doThrow(new CustomerServiceException("not found")).when(customerRepository).getCustomerPhoneNumber(any());
    Exception ex = assertThrows(CustomerServiceException.class, () -> customerDataService.getPhoneNumber(any()));
  }

  @Test
  public void getValidPhoneNumbertest_CustomerService() throws Exception {

    ArrayList<String> mockList = new ArrayList<>();
    mockList.add("789065444");
    mockList.add("789888944");
    when(customerRepository.getCustomerPhoneNumber(any())).thenReturn(mockList);
    ArrayList<String> result = customerDataService.getPhoneNumber("11111");
    Assertions.assertTrue(result.size() > 1);
  }

  @Test
  public void ValidTest_getAllPhoneNumbertest_CustomerService() throws Exception {
    ArrayList<String> mockList = new ArrayList<>();
    mockList.add("789065444");
    mockList.add("789888944");
    when(customerRepository.getAllCustomerPhoneNumber()).thenReturn(mockList);
    ArrayList<String> result = customerDataService.getAllPhoneNumbers();
    Assertions.assertTrue(result.size() > 1);
  }


}


