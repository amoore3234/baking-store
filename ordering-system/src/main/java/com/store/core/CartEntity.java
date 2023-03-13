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
import java.util.Objects;

@Entity
@Table(name = "carts")
public class CartEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @NotNull
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "product_id")
  private ProductEntity product;

  @Nullable
  @Column(name = "cart_quantity")
  private int cartQuantity;

  public CartEntity() {
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
  public int getCartQuantity() {
    return cartQuantity;
  }

  public void setCartQuantity(@Nullable int cartQuantity) {
    this.cartQuantity = cartQuantity;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, product, cartQuantity);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CartEntity)) {
      return false;
    }
    CartEntity other = (CartEntity) obj;
    return id == other.id && Objects.equals(product, other.product)
      && cartQuantity == other.cartQuantity;
  }

  @Override
  public String toString() {
    return "CartEntity [id=" + id + ", product=" + product + ", cartQuantity=" + cartQuantity + "]";
  }



}
