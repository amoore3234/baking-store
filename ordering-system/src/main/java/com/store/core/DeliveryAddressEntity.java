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
@Table(name = "delivery_address")
public class DeliveryAddressEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_address_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @Column(name = "delivery_address_one")
  private String deliveryAddressOne;

  @Nullable
  @Column(name = "delivery_address_two")
  private String deliveryAddressTwo;

  @Nullable
  @Column(name = "delivery_address_city")
  private String deliveryAddressCity;

  @Nullable
  @Column(name = "delivery_address_state")
  private String deliveryAddressState;

  @Nullable
  @Column(name = "delivery_address_zipcode")
  private String deliveryAddressZipCode;

  @Nullable
  @Column(name = "delivery_address_zipcode")
  private String deliveryAddressPhoneNumber;

  @Nullable
  @Column(name = "delivery_address_email")
  private String deliveryAddressEmail;

  @Nullable
  @OneToMany(mappedBy = "deliveryAddress")
  private List<OrderDetailEntity> orderDetails;

  public DeliveryAddressEntity() {
  }

  @Nullable
  public long id() {
    return id;
  }

  @Nullable
  public String getDeliveryAddressOne() {
    return deliveryAddressOne;
  }

  public void setDeliveryAddressOne(@Nullable String deliveryAddressOne) {
    this.deliveryAddressOne = deliveryAddressOne;
  }

  @Nullable
  public String getDeliveryAddressTwo() {
    return deliveryAddressTwo;
  }

  public void setDeliveryAddressTwo(@Nullable String deliveryAddressTwo) {
    this.deliveryAddressTwo = deliveryAddressTwo;
  }

  @Nullable
  public String getDeliveryAddressCity() {
    return deliveryAddressCity;
  }

  public void setDeliveryAddressCity(@Nullable String deliveryAddressCity) {
    this.deliveryAddressCity = deliveryAddressCity;
  }

  @Nullable
  public String getDeliveryAddressState() {
    return deliveryAddressState;
  }

  public void setDeliveryAddressState(@Nullable String deliveryAddressState) {
    this.deliveryAddressState = deliveryAddressState;
  }

  @Nullable
  public String getDeliveryAddressZipCode() {
    return deliveryAddressZipCode;
  }

  public void setDeliveryAddressZipCode(@Nullable String deliveryAddressZipCode) {
    this.deliveryAddressZipCode = deliveryAddressZipCode;
  }

  @Nullable
  public String getDeliveryAddressPhoneNumber() {
    return deliveryAddressPhoneNumber;
  }

  public void setDeliveryAddressPhoneNumber(@Nullable String deliveryAddressPhoneNumber) {
    this.deliveryAddressPhoneNumber = deliveryAddressPhoneNumber;
  }

  @Nullable
  public String getDeliveryAddressEmail() {
    return deliveryAddressEmail;
  }

  public void setDeliveryAddressEmail(@Nullable String deliveryAddressEmail) {
    this.deliveryAddressEmail = deliveryAddressEmail;
  }

  @Nullable
  public List<OrderDetailEntity> getOrderDetails() {
    return this.orderDetails;
  }

  public void setOrderDetails(@Nullable List<OrderDetailEntity> orderDetails) {
    this.orderDetails = orderDetails;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((deliveryAddressOne == null) ? 0 : deliveryAddressOne.hashCode());
    result = prime * result + ((deliveryAddressTwo == null) ? 0 : deliveryAddressTwo.hashCode());
    result = prime * result + ((deliveryAddressCity == null) ? 0 : deliveryAddressCity.hashCode());
    result = prime * result + ((deliveryAddressState == null) ? 0 : deliveryAddressState.hashCode());
    result = prime * result + ((deliveryAddressZipCode == null) ? 0 : deliveryAddressZipCode.hashCode());
    result = prime * result + ((deliveryAddressPhoneNumber == null) ? 0 : deliveryAddressPhoneNumber.hashCode());
    result = prime * result + ((deliveryAddressEmail == null) ? 0 : deliveryAddressEmail.hashCode());
    result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
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
    DeliveryAddressEntity other = (DeliveryAddressEntity) obj;
    if (id != other.id)
      return false;
    if (deliveryAddressOne == null) {
      if (other.deliveryAddressOne != null)
        return false;
    } else if (!deliveryAddressOne.equals(other.deliveryAddressOne))
      return false;
    if (deliveryAddressTwo == null) {
      if (other.deliveryAddressTwo != null)
        return false;
    } else if (!deliveryAddressTwo.equals(other.deliveryAddressTwo))
      return false;
    if (deliveryAddressCity == null) {
      if (other.deliveryAddressCity != null)
        return false;
    } else if (!deliveryAddressCity.equals(other.deliveryAddressCity))
      return false;
    if (deliveryAddressState == null) {
      if (other.deliveryAddressState != null)
        return false;
    } else if (!deliveryAddressState.equals(other.deliveryAddressState))
      return false;
    if (deliveryAddressZipCode == null) {
      if (other.deliveryAddressZipCode != null)
        return false;
    } else if (!deliveryAddressZipCode.equals(other.deliveryAddressZipCode))
      return false;
    if (deliveryAddressPhoneNumber == null) {
      if (other.deliveryAddressPhoneNumber != null)
        return false;
    } else if (!deliveryAddressPhoneNumber.equals(other.deliveryAddressPhoneNumber))
      return false;
    if (deliveryAddressEmail == null) {
      if (other.deliveryAddressEmail != null)
        return false;
    } else if (!deliveryAddressEmail.equals(other.deliveryAddressEmail))
      return false;
    if (orderDetails == null) {
      if (other.orderDetails != null)
        return false;
    } else if (!orderDetails.equals(other.orderDetails))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DeliveryAddressEntity [id=" + id + ", deliveryAddressOne=" + deliveryAddressOne + ", deliveryAddressTwo="
        + deliveryAddressTwo + ", deliveryAddressCity=" + deliveryAddressCity + ", deliveryAddressState="
        + deliveryAddressState + ", deliveryAddressZipCode=" + deliveryAddressZipCode + ", deliveryAddressPhoneNumber="
        + deliveryAddressPhoneNumber + ", deliveryAddressEmail=" + deliveryAddressEmail + ", orderDetails="
        + orderDetails + "]";
  }

}
