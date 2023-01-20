package com.store.core;

import java.sql.Timestamp;

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
  @Column(name = "date")
  private Timestamp date;

  @Nullable
  @Column(name = "order_total")
  private int orderTotal;

  public OrderEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  @Nullable
  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(@Nullable ProductEntity product) {
    this.product = product;
  }

  @Nullable
  public Timestamp getDate() {
    return date;
  }

  public void setDate(@Nullable Timestamp date) {
    this.date = date;
  }

  @Nullable
  public int getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(@Nullable int orderTotal) {
    this.orderTotal = orderTotal;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((orderDetail == null) ? 0 : orderDetail.hashCode());
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + orderTotal;
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
    OrderEntity other = (OrderEntity) obj;
    if (id != other.id)
      return false;
    if (orderDetail == null) {
      if (other.orderDetail != null)
        return false;
    } else if (!orderDetail.equals(other.orderDetail))
      return false;
    if (product == null) {
      if (other.product != null)
        return false;
    } else if (!product.equals(other.product))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (orderTotal != other.orderTotal)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "OrderEntity [id=" + id + ", orderDetail=" + orderDetail + ", product=" + product + ", date=" + date
        + ", orderTotal=" + orderTotal + "]";
  }

}
