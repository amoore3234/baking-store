package com.store.core;

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

import java.util.List;
import java.util.Objects;

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
  @JoinColumn(name = "shipping_address_id", referencedColumnName = "shipping_address_id")
  private ShippingAddressEntity shippingAddress;

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

  public void setId(@Nullable long id) {
    this.id = id;
  }

  @Nullable
  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(@Nullable CustomerEntity customer) {
    this.customer = customer;
  }

  @Nullable
  public ShippingAddressEntity getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(@Nullable ShippingAddressEntity shippingAddress) {
    this.shippingAddress = shippingAddress;
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
    return Objects.hash(id, customer, shippingAddress, paymentType, orderDetailTotal, orders);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof OrderDetailEntity)) {
      return false;
    }
    OrderDetailEntity other = (OrderDetailEntity) obj;
    return id == other.id && Objects.equals(customer, other.customer)
        && Objects.equals(shippingAddress, other.shippingAddress)
        && Objects.equals(paymentType, other.paymentType)
        && orderDetailTotal == other.orderDetailTotal && Objects.equals(orders, other.orders);
  }

  @Override
  public String toString() {
    return "OrderDetailEntity [id=" + id + ", customer=" + customer
      + ", shippingAddress=" + shippingAddress + ", paymentType=" + paymentType
      + ", orderDetailTotal=" + orderDetailTotal + ", orders=" + orders + "]";
  }

}
