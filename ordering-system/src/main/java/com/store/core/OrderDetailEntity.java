package com.store.core;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "order_details")
public class OrderDetailEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_detail_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  private CustomerEntity customer;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "delivery_address_id", referencedColumnName = "delivery_address_id")
  private DeliveryAddressEntity deliveryAddress;

  @Nullable
  @Column(name = "payment_type")
  private String paymentType;

  @Nullable
  @Column(name = "order_detail_total")
  private int orderDetailTotal;

  @Nullable
  @OneToMany(mappedBy = "orderDetail")
  private List<OrderEntity> orders;

  public OrderDetailEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  @Nullable
  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(@Nullable CustomerEntity customer) {
    this.customer = customer;
  }

  @Nullable
  public DeliveryAddressEntity getDeliveryAddress() {
    return deliveryAddress;
  }

  public void setDeliveryAddress(@Nullable DeliveryAddressEntity deliveryAddress) {
    this.deliveryAddress = deliveryAddress;
  }

  @Nullable
  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(@Nullable String paymentType) {
    this.paymentType = paymentType;
  }

  @Nullable
  public int getOrderDetailTotal() {
    return orderDetailTotal;
  }

  public void setOrderDetailTotal(@Nullable int orderDetailTotal) {
    this.orderDetailTotal = orderDetailTotal;
  }

  public List<OrderEntity> getOrders() {
    return orders;
  }

  public void setOrders(List<OrderEntity> orders) {
    this.orders = orders;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((customer == null) ? 0 : customer.hashCode());
    result = prime * result + ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
    result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
    result = prime * result + orderDetailTotal;
    result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
    OrderDetailEntity other = (OrderDetailEntity) obj;
    if (id != other.id)
      return false;
    if (customer == null) {
      if (other.customer != null)
        return false;
    } else if (!customer.equals(other.customer))
      return false;
    if (deliveryAddress == null) {
      if (other.deliveryAddress != null)
        return false;
    } else if (!deliveryAddress.equals(other.deliveryAddress))
      return false;
    if (paymentType == null) {
      if (other.paymentType != null)
        return false;
    } else if (!paymentType.equals(other.paymentType))
      return false;
    if (orderDetailTotal != other.orderDetailTotal)
      return false;
    if (orders == null) {
      if (other.orders != null)
        return false;
    } else if (!orders.equals(other.orders))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "OrderDetailEntity [id=" + id + ", customer=" + customer + ", deliveryAddress=" + deliveryAddress
        + ", paymentType=" + paymentType + ", orderDetailTotal=" + orderDetailTotal + ", orders=" + orders + "]";
  }

}
