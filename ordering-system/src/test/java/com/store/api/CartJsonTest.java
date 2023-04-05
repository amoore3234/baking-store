package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.CartEntity;
import com.store.core.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();
  private CartEntity cart;
  private ProductEntity product;

  @BeforeEach
  void setUp() {
    product = new ProductEntity();
    product.setId(1L);
    product.setProductName("Cupcake cups");
    product.setProductType("Cakes");
    product.setProductPrice(5.95);
    product.setProductDescription("Made out of baking sheets");
    product.setProductQuantity(100);

    cart = new CartEntity();
    cart.setProduct(product);
    cart.setCartQuantity(1);
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(getClass().getResource("/api/cart.json"), CartEntity.class));

    assertThat(MAPPER.writeValueAsString(cart)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final long productId = 1L;
    final int cartQuantity = 1;

    final CartEntity cartJson = MAPPER.readValue(getClass()
        .getResource("/api/cart.json"), CartEntity.class);

    assertThat(cartJson.getProduct().getId()).isEqualTo(productId);
    assertThat(cartJson.getCartQuantity()).isEqualTo(cartQuantity);
  }
}
