package com.store.core;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {

  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", nullable = false, unique = true)
  private long id;

  @Nullable
  @Column(name = "product_name")
  private String productName;

  @Nullable
  @Column(name = "product_price")
  private double productPrice;

  @Nullable
  @Column(name = "payment_description")
  private String productDescription;

  @Nullable
  @Column(name = "payment_quantity")
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

  @Nullable
  public String getProductName() {
    return productName;
  }

  public void setProductName(@Nullable String productName) {
    this.productName = productName;
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
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((productName == null) ? 0 : productName.hashCode());
    long temp;
    temp = Double.doubleToLongBits(productPrice);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((productDescription == null) ? 0 : productDescription.hashCode());
    result = prime * result + productQuantity;
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
    ProductEntity other = (ProductEntity) obj;
    if (id != other.id)
      return false;
    if (productName == null) {
      if (other.productName != null)
        return false;
    } else if (!productName.equals(other.productName))
      return false;
    if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
      return false;
    if (productDescription == null) {
      if (other.productDescription != null)
        return false;
    } else if (!productDescription.equals(other.productDescription))
      return false;
    if (productQuantity != other.productQuantity)
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
    return "ProductEntity [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice
        + ", productDescription=" + productDescription + ", productQuantity=" + productQuantity + ", orders=" + orders
        + "]";
  }

}
