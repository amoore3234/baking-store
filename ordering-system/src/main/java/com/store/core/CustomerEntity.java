package com.store.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable {

  @Id
  @Nullable
  @JsonProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @JsonProperty
  @Column(name = "customer_first_name")
  private String customerFirstName;

  @Nullable
  @JsonProperty
  @Column(name = "customer_last_name")
  private String customerLastName;

  @Nullable
  @JsonProperty
  @Column(name = "customer_address_one")
  private String customerAddressOne;

  @Nullable
  @JsonProperty
  @Column(name = "customer_address_two")
  private String customerAddressTwo;

  @Nullable
  @JsonProperty
  @Column(name = "customer_city")
  private String customerCity;

  @Nullable
  @JsonProperty
  @Column(name = "customer_state")
  private String customerState;

  @Nullable
  @JsonProperty
  @Column(name = "customer_zip_code")
  private String customerZipCode;

  @Nullable
  @JsonProperty
  @Column(name = "customer_phone_number")
  private String customerPhoneNumber;

  @Nullable
  @JsonProperty
  @Column(name = "customer_email")
  private String customerEmail;

  @Nullable
  @JsonProperty
  @OneToMany(mappedBy = "customer")
  private List<OrderDetailEntity> customers;

  @Nullable
  @JsonProperty
  @OneToMany(mappedBy = "customer")
  private List<CustomerCartEntity> customerCarts;

  public CustomerEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  public void setId(@Nullable long id) {
    this.id = id;
  }

  @Nullable
  public String getCustomerFirstName() {
    return customerFirstName;
  }

  public void setCustomerFirstName(@Nullable String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  @Nullable
  public String getCustomerLastName() {
    return customerLastName;
  }

  public void setCustomerLastName(@Nullable String customerLastName) {
    this.customerLastName = customerLastName;
  }

  @Nullable
  public String getCustomerAddressOne() {
    return customerAddressOne;
  }

  public void setCustomerAddressOne(@Nullable String customerAddressOne) {
    this.customerAddressOne = customerAddressOne;
  }

  @Nullable
  public String getCustomerAddressTwo() {
    return customerAddressTwo;
  }

  public void setCustomerAddressTwo(@Nullable String customerAddressTwo) {
    this.customerAddressTwo = customerAddressTwo;
  }

  @Nullable
  public String getCustomerCity() {
    return customerCity;
  }

  public void setCustomerCity(@Nullable String customerCity) {
    this.customerCity = customerCity;
  }

  @Nullable
  public String getCustomerState() {
    return customerState;
  }

  public void setCustomerState(@Nullable String customerState) {
    this.customerState = customerState;
  }

  @Nullable
  public String getCustomerZipCode() {
    return customerZipCode;
  }

  public void setCustomerZipCode(@Nullable String customerZipCode) {
    this.customerZipCode = customerZipCode;
  }

  @Nullable
  public String getCustomerPhoneNumber() {
    return customerPhoneNumber;
  }

  public void setCustomerPhoneNumber(@Nullable String customerPhoneNumber) {
    this.customerPhoneNumber = customerPhoneNumber;
  }

  @Nullable
  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(@Nullable String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public List<OrderDetailEntity> getCustomers() {
    return customers;
  }

  public void setCustomers(List<OrderDetailEntity> customers) {
    this.customers = customers;
  }

  @Nullable
  public List<CustomerCartEntity> getCustomerCarts() {
    return customerCarts;
  }

  public void setCustomerCarts(@Nullable List<CustomerCartEntity> customerCarts) {
    this.customerCarts = customerCarts;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerFirstName, customerLastName, customerAddressOne,
    customerAddressTwo, customerCity, customerState, customerZipCode,
    customerPhoneNumber, customerEmail, customers, customerCarts);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CustomerEntity)) {
      return false;
    }
    CustomerEntity other = (CustomerEntity) obj;
    return id == other.id && Objects.equals(customerFirstName, other.customerFirstName)
        && Objects.equals(customerLastName, other.customerLastName)
        && Objects.equals(customerAddressOne, other.customerAddressOne)
        && Objects.equals(customerAddressTwo, other.customerAddressTwo)
        && Objects.equals(customerCity, other.customerCity)
        && Objects.equals(customerState, other.customerState)
        && Objects.equals(customerZipCode, other.customerZipCode)
        && Objects.equals(customerPhoneNumber, other.customerPhoneNumber)
        && Objects.equals(customerEmail, other.customerEmail)
        && Objects.equals(customers, other.customers)
        && Objects.equals(customerCarts, other.customerCarts);
  }

  @Override
  public String toString() {
    return "CustomerEntity [id=" + id + ", customerFirstName="
      + customerFirstName + ", customerLastName=" + customerLastName + ", customerAddressOne="
      + customerAddressOne + ", customerAddressTwo=" + customerAddressTwo
      + ", customerCity=" + customerCity + ", customerState="
      + customerState + ", customerZipCode=" + customerZipCode
      + ", customerPhoneNumber=" + customerPhoneNumber + ", customerEmail="
      + customerEmail + ", customers=" + customers + ", customerCarts=" + customerCarts + "]";
  }



}
