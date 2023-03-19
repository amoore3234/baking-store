package com.store.core;

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

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @Column(name = "product_name")
  private String productName;

  @Nullable
  @Column(name = "product_type")
  private String productType;

  @Nullable
  @Column(name = "product_price")
  private double productPrice;

  @Nullable
  @Column(name = "product_description")
  private String productDescription;

  @Nullable
  @Column(name = "product_quantity")
  private int productQuantity;

  @Nullable
  @OneToMany(mappedBy = "product")
  private List<OrderEntity> orders;

  public ProductEntity() {
  }

  @Nullable
  public long getId() {
    return id;
  }

  public void setId(@Nullable long id) {
    this.id = id;
  }

  @Nullable
  public String getProductName() {
    return productName;
  }

  public void setProductName(@Nullable String productName) {
    this.productName = productName;
  }

  @Nullable
  public String getProductType() {
    return productType;
  }

  public void setProductType(@Nullable String productType) {
    this.productType = productType;
  }

  @Nullable
  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(@Nullable double productPrice) {
    this.productPrice = productPrice;
  }

  @Nullable
  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(@Nullable String productDescription) {
    this.productDescription = productDescription;
  }

  @Nullable
  public int getProductQuantity() {
    return productQuantity;
  }

  public void setProductQuantity(@Nullable int productQuantity) {
    this.productQuantity = productQuantity;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productName, productType, productPrice,
      productDescription, productQuantity, orders);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ProductEntity)) {
      return false;
    }
    ProductEntity other = (ProductEntity) obj;
    return id == other.id && Objects.equals(productName, other.productName)
        && Objects.equals(productType, other.productType)
        && Double.doubleToLongBits(productPrice) == Double.doubleToLongBits(other.productPrice)
        && Objects.equals(productDescription, other.productDescription)
        && productQuantity == other.productQuantity
        && Objects.equals(orders, other.orders);
  }

  @Override
  public String toString() {
    return "ProductEntity [id=" + id + ", productName=" + productName + ", productType="
      + productType + ", productPrice=" + productPrice + ", productDescription="
      + productDescription + ", productQuantity=" + productQuantity + ", orders=" + orders + "]";
  }

}
