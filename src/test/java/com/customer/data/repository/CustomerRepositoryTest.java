package com.customer.data.repository;

import com.customer.data.application.model.Customer;
import com.customer.data.application.model.PhoneNumberDetails;
import com.customer.data.exception.CustomerServiceException;
import com.customer.data.repository.provider.CustomerDetailsRepositoryProvider;
import com.customer.data.repository.provider.PhoneNumberDetailsRepositoryProvider;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustomerRepositoryTest {
  @Mock
  private CustomerDetailsRepositoryProvider customerDetailsRepositoryProvider;

  @Mock
  private PhoneNumberDetailsRepositoryProvider phoneNumberDetailsRepositoryProvider;


  @InjectMocks
  CustomerRepository customerRepository;

  private static PhoneNumberDetails phoneNumberDetails = new PhoneNumberDetails();
  private static PhoneNumberDetails customer1 = new PhoneNumberDetails("111111111", "Y", null);
  private static PhoneNumberDetails customer2 = new PhoneNumberDetails("222222222", "N", null);
  private static Customer customer = new Customer();
  private static Optional<PhoneNumberDetails> phoneDetails;
  private static Optional<Customer> customerDetails;
  private static List<PhoneNumberDetails> customerList = new ArrayList<>();
  List<PhoneNumberDetails> phoneNumberList;

  @BeforeAll()
  public static void setup() {
    phoneDetails = Optional.of(phoneNumberDetails);
    customerDetails = Optional.of(customer);
    customerList.add(customer1);
    customerList.add(customer2);
  }

  @Test
  public void activateNonexistantPhoneNumbertest_Exception_CustomerRepository() throws CustomerServiceException {
    assertThrows(CustomerServiceException.class, () -> customerRepository.activateNumber(any()));
  }


  @Test
  public void activateValidPhoneNumbertest_CustomerRepository() throws CustomerServiceException {

    when(phoneNumberDetailsRepositoryProvider.findById(any())).thenReturn(phoneDetails);
    assertDoesNotThrow(() -> customerRepository.activateNumber("0401222222"));
  }


  @Test
  public void getNonexistantPhoneNumbertest_Exception_CustomerRepository() throws Exception {
    Exception ex = assertThrows(CustomerServiceException.class, () -> customerRepository.getCustomerPhoneNumber(any()));
  }


  @Test
  public void getValidPhoneNumbertest() throws Exception {

    when(customerDetailsRepositoryProvider.findById(any())).thenReturn(customerDetails);
    assertDoesNotThrow(() -> customerRepository.getCustomerPhoneNumber("11111"));
    verify(customerDetailsRepositoryProvider, times(1)).findById(any());

  }


  @Test
  public void ValidTest_getAllPhoneNumbertest() throws Exception {
    when(phoneNumberDetailsRepositoryProvider.findAll()).thenReturn(customerList);
    customerRepository.getAllCustomerPhoneNumber();
    verify(phoneNumberDetailsRepositoryProvider, times(1)).findAll();

  }

}


