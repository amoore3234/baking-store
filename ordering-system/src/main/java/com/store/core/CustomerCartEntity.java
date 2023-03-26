package com.store.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
  public CartEntity getCart() {
    return cart;
  }

  public void setCart(@Nullable CartEntity cart) {
    this.cart = cart;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, cart);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CustomerCartEntity)) {
      return false;
    }
    CustomerCartEntity other = (CustomerCartEntity) obj;
    return id == other.id && Objects.equals(customer, other.customer)
      && Objects.equals(cart, other.cart);
  }

  @Override
  public String toString() {
    return "CustomerCartEntity [id=" + id + ", customer=" + customer + ", cart=" + cart + "]";
  }

}
