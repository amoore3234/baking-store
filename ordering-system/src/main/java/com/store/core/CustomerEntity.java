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
  @Column(name = "customer_first_name")
  private String customerFirstName;

  @Nullable
  @Column(name = "customer_last_name")
  private String customerLastName;

  @Nullable
  @Column(name = "customer_address_one")
  private String customerAddressOne;

  @Nullable
  @Column(name = "customer_address_two")
  private String customerAddressTwo;

  @Nullable
  @Column(name = "customer_city")
  private String customerCity;

  @Nullable
  @Column(name = "customer_state")
  private String customerState;

  @Nullable
  @Column(name = "customer_zip_code")
  private String customerZipCode;

  @Nullable
  @Column(name = "customer_phone_number")
  private String customerPhoneNumber;

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
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((customerFirstName == null) ? 0 : customerFirstName.hashCode());
    result = prime * result + ((customerLastName == null) ? 0 : customerLastName.hashCode());
    result = prime * result + ((customerAddressOne == null) ? 0 : customerAddressOne.hashCode());
    result = prime * result + ((customerAddressTwo == null) ? 0 : customerAddressTwo.hashCode());
    result = prime * result + ((customerCity == null) ? 0 : customerCity.hashCode());
    result = prime * result + ((customerState == null) ? 0 : customerState.hashCode());
    result = prime * result + ((customerZipCode == null) ? 0 : customerZipCode.hashCode());
    result = prime * result + ((customerPhoneNumber == null) ? 0 : customerPhoneNumber.hashCode());
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
    if (customerFirstName == null) {
      if (other.customerFirstName != null)
        return false;
    } else if (!customerFirstName.equals(other.customerFirstName))
      return false;
    if (customerLastName == null) {
      if (other.customerLastName != null)
        return false;
    } else if (!customerLastName.equals(other.customerLastName))
      return false;
    if (customerAddressOne == null) {
      if (other.customerAddressOne != null)
        return false;
    } else if (!customerAddressOne.equals(other.customerAddressOne))
      return false;
    if (customerAddressTwo == null) {
      if (other.customerAddressTwo != null)
        return false;
    } else if (!customerAddressTwo.equals(other.customerAddressTwo))
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
    if (customerZipCode == null) {
      if (other.customerZipCode != null)
        return false;
    } else if (!customerZipCode.equals(other.customerZipCode))
      return false;
    if (customerPhoneNumber == null) {
      if (other.customerPhoneNumber != null)
        return false;
    } else if (!customerPhoneNumber.equals(other.customerPhoneNumber))
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

  @Override
  public String toString() {
    return "CustomerEntity [id=" + id + ", customerFirstName=" + customerFirstName + ", customerLastName="
        + customerLastName + ", customerAddressOne=" + customerAddressOne + ", customerAddressTwo=" + customerAddressTwo
        + ", customerCity=" + customerCity + ", customerState=" + customerState + ", customerZipCode=" + customerZipCode
        + ", customerPhoneNumber=" + customerPhoneNumber + ", customerEmail=" + customerEmail + ", customers="
        + customers + ", customerCarts=" + customerCarts + "]";
  }

}
