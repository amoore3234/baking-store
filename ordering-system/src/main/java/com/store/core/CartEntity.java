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
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    result = prime * result + cartQuantity;
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
    CartEntity other = (CartEntity) obj;
    if (id != other.id)
      return false;
    if (product == null) {
      if (other.product != null)
        return false;
    } else if (!product.equals(other.product))
      return false;
    if (cartQuantity != other.cartQuantity)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CartEntity [id=" + id + ", product=" + product + ", cartQuantity=" + cartQuantity + "]";
  }

}
