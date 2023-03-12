package com.store.core;

import java.time.OffsetDateTime;
import java.util.Objects;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "order_detail_id", referencedColumnName = "order_detail_id")
  private OrderDetailEntity orderDetail;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "product_id")
  private ProductEntity product;

  @Nullable
  @Column(name = "order_date")
  private OffsetDateTime orderDate;

  @Nullable
  @Column(name = "order_total")
  private double orderTotal;

  public OrderEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  public void setId(@Nullable long id) {
    this.id = id;
  }

  @Nullable
  public OrderDetailEntity getOrderDetail() {
    return orderDetail;
  }

  public void setOrderDetail(@Nullable OrderDetailEntity orderDetail) {
    this.orderDetail = orderDetail;
  }

  @Nullable
  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(@Nullable ProductEntity product) {
    this.product = product;
  }

  @Nullable
  public OffsetDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(@Nullable OffsetDateTime orderDate) {
    this.orderDate = orderDate;
  }

  @Nullable
  public double getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(@Nullable double orderTotal) {
    this.orderTotal = orderTotal;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderDetail, product, orderDate, orderTotal);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof OrderEntity)) {
      return false;
    }
    OrderEntity other = (OrderEntity) obj;
    return id == other.id && Objects.equals(orderDetail, other.orderDetail) && Objects.equals(product, other.product)
        && Objects.equals(orderDate, other.orderDate)
        && Double.doubleToLongBits(orderTotal) == Double.doubleToLongBits(other.orderTotal);
  }

  @Override
  public String toString() {
    return "OrderEntity [id=" + id + ", orderDetail=" + orderDetail + ", product=" + product + ", orderDate="
        + orderDate + ", orderTotal=" + orderTotal + "]";
  }

}
