package com.store.core;

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
@Table(name = "customer_carts")
public class CustomerCartEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_cart_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  private CustomerEntity customer;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
  private CartEntity cart;

  public CustomerCartEntity() {
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
  public CartEntity getCart() {
    return cart;
  }

  public void setCart(@Nullable CartEntity cart) {
    this.cart = cart;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((customer == null) ? 0 : customer.hashCode());
    result = prime * result + ((cart == null) ? 0 : cart.hashCode());
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
    CustomerCartEntity other = (CustomerCartEntity) obj;
    if (id != other.id)
      return false;
    if (customer == null) {
      if (other.customer != null)
        return false;
    } else if (!customer.equals(other.customer))
      return false;
    if (cart == null) {
      if (other.cart != null)
        return false;
    } else if (!cart.equals(other.cart))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CustomerCart [id=" + id + ", customer=" + customer + ", cart=" + cart + "]";
  }

}
