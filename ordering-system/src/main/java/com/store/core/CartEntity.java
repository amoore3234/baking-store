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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Carts class modeled as an entity class.
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name = "carts")
public class CartEntity implements Serializable {

  @Id
  @Nullable
  @JsonProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @NotNull
  @JsonProperty
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "product_id")
  private ProductEntity product;

  @Nullable
  @JsonProperty
  @Column(name = "cart_quantity")
  private int cartQuantity;

  @Nullable
  @JsonProperty
  @OneToMany(mappedBy = "cart")
  private List<CustomerCartEntity> carts;

  public CartEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  public void setId(@Nullable long id) {
    this.id = id;
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

  @Nullable
  public List<CustomerCartEntity> getCarts() {
    return carts;
  }

  public void setCarts(@Nullable List<CustomerCartEntity> carts) {
    this.carts = carts;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, product, cartQuantity, carts);
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
      && cartQuantity == other.cartQuantity && Objects.equals(carts, other.carts);
  }

  @Override
  public String toString() {
    return "CartEntity [id=" + id + ", product=" + product + ", cartQuantity="
      + cartQuantity + ", carts=" + carts + "]";
  }

}
