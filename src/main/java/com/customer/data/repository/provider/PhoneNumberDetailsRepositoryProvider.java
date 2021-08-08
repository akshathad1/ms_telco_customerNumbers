package com.customer.data.repository.provider;

import com.customer.data.application.model.PhoneNumberDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberDetailsRepositoryProvider extends JpaRepository<PhoneNumberDetails,String> {

}
