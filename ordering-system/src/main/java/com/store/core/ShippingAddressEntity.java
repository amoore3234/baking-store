package com.store.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

/**
 * ShippingAddress class modeled as an entity class.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name = "shipping_address")
public class ShippingAddressEntity implements Serializable {

  @Id
  @Nullable
  @JsonProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "shipping_address_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_name")
  private String shippingAddressName;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_one")
  private String shippingAddressOne;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_two")
  private String shippingAddressTwo;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_city")
  private String shippingAddressCity;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_state")
  private String shippingAddressState;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_zip_code")
  private String shippingAddressZipCode;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_phone")
  private String shippingAddressPhoneNumber;

  @Nullable
  @JsonProperty
  @Column(name = "shipping_address_email")
  private String shippingAddressEmail;

  @Nullable
  @JsonProperty
  @OneToMany(mappedBy = "shippingAddress")
  private List<OrderDetailEntity> orderDetails;

  public ShippingAddressEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  public void setId(@Nullable long id) {
    this.id = id;
  }

  @Nullable
  public String getShippingAddressName() {
    return shippingAddressName;
  }

  public void setShippingAddressName(@Nullable String shippingAddressName) {
    this.shippingAddressName = shippingAddressName;
  }

  @Nullable
  public String getShippingAddressOne() {
    return shippingAddressOne;
  }

  public void setShippingAddressOne(@Nullable String shippingAddressOne) {
    this.shippingAddressOne = shippingAddressOne;
  }

  @Nullable
  public String getShippingAddressTwo() {
    return shippingAddressTwo;
  }

  public void setShippingAddressTwo(@Nullable String shippingAddressTwo) {
    this.shippingAddressTwo = shippingAddressTwo;
  }

  @Nullable
  public String getShippingAddressCity() {
    return shippingAddressCity;
  }

  public void setShippingAddressCity(@Nullable String shippingAddressCity) {
    this.shippingAddressCity = shippingAddressCity;
  }

  @Nullable
  public String getShippingAddressState() {
    return shippingAddressState;
  }

  public void setShippingAddressState(@Nullable String shippingAddressState) {
    this.shippingAddressState = shippingAddressState;
  }

  @Nullable
  public String getShippingAddressZipCode() {
    return shippingAddressZipCode;
  }

  public void setShippingAddressZipCode(@Nullable String shippingAddressZipCode) {
    this.shippingAddressZipCode = shippingAddressZipCode;
  }

  @Nullable
  public String getShippingAddressPhoneNumber() {
    return shippingAddressPhoneNumber;
  }

  public void setShippingAddressPhoneNumber(@Nullable String shippingAddressPhoneNumber) {
    this.shippingAddressPhoneNumber = shippingAddressPhoneNumber;
  }

  @Nullable
  public String getShippingAddressEmail() {
    return shippingAddressEmail;
  }

  public void setShippingAddressEmail(@Nullable String shippingAddressEmail) {
    this.shippingAddressEmail = shippingAddressEmail;
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
    return Objects.hash(id, shippingAddressName, shippingAddressOne, shippingAddressTwo,
    shippingAddressCity, shippingAddressState, shippingAddressZipCode, shippingAddressPhoneNumber,
    shippingAddressEmail, orderDetails);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ShippingAddressEntity)) {
      return false;
    }
    ShippingAddressEntity other = (ShippingAddressEntity) obj;
    return id == other.id && Objects.equals(shippingAddressName, other.shippingAddressName)
        && Objects.equals(shippingAddressOne, other.shippingAddressOne)
        && Objects.equals(shippingAddressTwo, other.shippingAddressTwo)
        && Objects.equals(shippingAddressCity, other.shippingAddressCity)
        && Objects.equals(shippingAddressState, other.shippingAddressState)
        && Objects.equals(shippingAddressZipCode, other.shippingAddressZipCode)
        && Objects.equals(shippingAddressPhoneNumber, other.shippingAddressPhoneNumber)
        && Objects.equals(shippingAddressEmail, other.shippingAddressEmail)
        && Objects.equals(orderDetails, other.orderDetails);
  }

  @Override
  public String toString() {
    return "ShippingAddressEntity [id=" + id + ", shippingAddressName="
      + shippingAddressName + ", shippingAddressOne="
      + shippingAddressOne + ", shippingAddressTwo="
      + shippingAddressTwo + ", shippingAddressCity="
      + shippingAddressCity + ", shippingAddressState="
      + shippingAddressState + ", shippingAddressZipCode="
      + shippingAddressZipCode + ", shippingAddressPhoneNumber="
      + shippingAddressPhoneNumber + ", shippingAddressEmail="
      + shippingAddressEmail + ", orderDetails=" + orderDetails + "]";
  }

}
