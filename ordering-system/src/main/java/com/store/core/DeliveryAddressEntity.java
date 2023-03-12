package com.store.core;

import java.util.List;
import java.util.Objects;
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
  @Column(name = "delivery_address_name")
  private String deliveryAddressName;

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
  @Column(name = "delivery_address_zip_code")
  private String deliveryAddressZipCode;

  @Nullable
  @Column(name = "delivery_address_phone")
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
  public long getId() {
    return id;
  }

  public void setId(@Nullable long id) {
    this.id = id;
  }

  @Nullable
  public String getDeliveryAddressName() {
    return deliveryAddressName;
  }

  public void setDeliveryAddressName(@Nullable String deliveryAddressName) {
    this.deliveryAddressName = deliveryAddressName;
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
    return Objects.hash(id, deliveryAddressName, deliveryAddressOne, deliveryAddressTwo, deliveryAddressCity,
        deliveryAddressState, deliveryAddressZipCode, deliveryAddressPhoneNumber, deliveryAddressEmail, orderDetails);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof DeliveryAddressEntity)) {
      return false;
    }
    DeliveryAddressEntity other = (DeliveryAddressEntity) obj;
    return id == other.id && Objects.equals(deliveryAddressName, other.deliveryAddressName)
        && Objects.equals(deliveryAddressOne, other.deliveryAddressOne)
        && Objects.equals(deliveryAddressTwo, other.deliveryAddressTwo)
        && Objects.equals(deliveryAddressCity, other.deliveryAddressCity)
        && Objects.equals(deliveryAddressState, other.deliveryAddressState)
        && Objects.equals(deliveryAddressZipCode, other.deliveryAddressZipCode)
        && Objects.equals(deliveryAddressPhoneNumber, other.deliveryAddressPhoneNumber)
        && Objects.equals(deliveryAddressEmail, other.deliveryAddressEmail)
        && Objects.equals(orderDetails, other.orderDetails);
  }

  @Override
  public String toString() {
    return "DeliveryAddressEntity [id=" + id + ", deliveryAddressName=" + deliveryAddressName + ", deliveryAddressOne="
        + deliveryAddressOne + ", deliveryAddressTwo=" + deliveryAddressTwo + ", deliveryAddressCity="
        + deliveryAddressCity + ", deliveryAddressState=" + deliveryAddressState + ", deliveryAddressZipCode="
        + deliveryAddressZipCode + ", deliveryAddressPhoneNumber=" + deliveryAddressPhoneNumber
        + ", deliveryAddressEmail=" + deliveryAddressEmail + ", orderDetails=" + orderDetails + "]";
  }

}
