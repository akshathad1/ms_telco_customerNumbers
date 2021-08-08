package com.customer.data.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER_PHONE_NUMBER")
@EqualsAndHashCode(exclude = "customer")
@ToString(exclude = "customer")
public class PhoneNumberDetails implements Serializable {

  @Id
  @Setter(AccessLevel.PRIVATE)
  @Column(name = "CUSTOMER_PHONENUMBER")
  private String customerPhoneNumber;

  @NonNull
  @Column(name = "ACTIVATION_STATUS")
  @Setter(AccessLevel.PUBLIC)
  private String activationStatus;

  @JsonIgnore
  public Customer getCustomer() {
    return customer;
  }

  @JsonIgnore
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ID", nullable = false)
  private Customer customer;

}