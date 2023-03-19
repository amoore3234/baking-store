package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();

  private ProductEntity product;

  @BeforeEach
  void setUp() {
    product = new ProductEntity();
    product.setProductName("Cupcake cups");
    product.setProductType("Cakes");
    product.setProductPrice(5.95);
    product.setProductDescription("Made out of baking sheets");
    product.setProductQuantity(100);
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(getClass().getResource("/api/products.json"), ProductEntity.class));

    assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final String name = "Cupcake cups";
    final String type= "Cakes";
    final double price = 5.95;
    final String description = "Made out of baking sheets";
    final int quantity = 100;

    final ProductEntity productJson = MAPPER.readValue(getClass()
        .getResource("/api/products.json"), ProductEntity.class);

    assertThat(productJson.getProductName()).isEqualTo(name);
    assertThat(productJson.getProductType()).isEqualTo(type);
    assertThat(productJson.getProductPrice()).isEqualTo(price);
    assertThat(productJson.getProductDescription()).isEqualTo(description);
    assertThat(productJson.getProductQuantity()).isEqualTo(quantity);
  }
}
