package com.customer.data.repository.provider;

import com.customer.data.application.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepositoryProvider extends JpaRepository<Customer,String> {

}
