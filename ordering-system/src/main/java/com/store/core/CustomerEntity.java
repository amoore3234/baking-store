package com.store.core;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @Column(name = "first_name")
  private String firstName;

  @Nullable
  @Column(name = "last_name")
  private String lastName;

  @Nullable
  @Column(name = "address_one")
  private String addressOne;

  @Nullable
  @Column(name = "address_two")
  private String addressTwo;

  @Nullable
  @Column(name = "customer_city")
  private String customerCity;

  @Nullable
  @Column(name = "customer_state")
  private String customerState;

  @Nullable
  @Column(name = "zip_code")
  private String zipCode;

  @Nullable
  @Column(name = "phone_number")
  private String phoneNumber;

  @Nullable
  @Column(name = "customer_email")
  private String customerEmail;

  @Nullable
  @OneToMany(mappedBy = "customer")
  private List<OrderDetailEntity> customers;

  @Nullable
  @OneToMany(mappedBy = "customer")
  private List<CustomerCartEntity> customerCarts;

  public CustomerEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  @Nullable
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(@Nullable String firstName) {
    this.firstName = firstName;
  }

  @Nullable
  public String getLastName() {
    return lastName;
  }

  public void setLastName(@Nullable String lastName) {
    this.lastName = lastName;
  }

  @Nullable
  public String getAddressOne() {
    return addressOne;
  }

  public void setAddressOne(@Nullable String addressOne) {
    this.addressOne = addressOne;
  }

  @Nullable
  public String getAddressTwo() {
    return addressTwo;
  }

  public void setAddressTwo(@Nullable String addressTwo) {
    this.addressTwo = addressTwo;
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
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(@Nullable String zipCode) {
    this.zipCode = zipCode;
  }

  @Nullable
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(@Nullable String phoneNumber) {
    this.phoneNumber = phoneNumber;
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

  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((addressOne == null) ? 0 : addressOne.hashCode());
    result = prime * result + ((addressTwo == null) ? 0 : addressTwo.hashCode());
    result = prime * result + ((customerCity == null) ? 0 : customerCity.hashCode());
    result = prime * result + ((customerState == null) ? 0 : customerState.hashCode());
    result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
    result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
    result = prime * result + ((customers == null) ? 0 : customers.hashCode());
    result = prime * result + ((customerCarts == null) ? 0 : customerCarts.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CustomerEntity other = (CustomerEntity) obj;
    if (id != other.id)
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (addressOne == null) {
      if (other.addressOne != null)
        return false;
    } else if (!addressOne.equals(other.addressOne))
      return false;
    if (addressTwo == null) {
      if (other.addressTwo != null)
        return false;
    } else if (!addressTwo.equals(other.addressTwo))
      return false;
    if (customerCity == null) {
      if (other.customerCity != null)
        return false;
    } else if (!customerCity.equals(other.customerCity))
      return false;
    if (customerState == null) {
      if (other.customerState != null)
        return false;
    } else if (!customerState.equals(other.customerState))
      return false;
    if (zipCode == null) {
      if (other.zipCode != null)
        return false;
    } else if (!zipCode.equals(other.zipCode))
      return false;
    if (phoneNumber == null) {
      if (other.phoneNumber != null)
        return false;
    } else if (!phoneNumber.equals(other.phoneNumber))
      return false;
    if (customerEmail == null) {
      if (other.customerEmail != null)
        return false;
    } else if (!customerEmail.equals(other.customerEmail))
      return false;
    if (customers == null) {
      if (other.customers != null)
        return false;
    } else if (!customers.equals(other.customers))
      return false;
    if (customerCarts == null) {
      if (other.customerCarts != null)
        return false;
    } else if (!customerCarts.equals(other.customerCarts))
      return false;
    return true;
  }

  public String toString() {
    return "CustomerEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", addressOne="
        + addressOne + ", addressTwo=" + addressTwo + ", customerCity=" + customerCity + ", customerState="
        + customerState + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber + ", customerEmail=" + customerEmail
        + ", customers=" + customers + ", customerCarts=" + customerCarts + "]";
  }

}
