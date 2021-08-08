package com.customer.data.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

  public Customer() {

  }


  @Id
  @GeneratedValue
  @Setter(AccessLevel.PRIVATE)
  @Column(name = "ID", nullable = false)
  private String customerId;

  @NonNull
  @Column(name = "CUSTOMER_NAME", nullable = false)
  @Setter(AccessLevel.PRIVATE)
  private String customerName;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private Set<PhoneNumberDetails> phoneNumbers = new HashSet<>();

  @JsonIgnore
  public Set<PhoneNumberDetails> getPhoneNumbers() {
    return phoneNumbers;
  }

}