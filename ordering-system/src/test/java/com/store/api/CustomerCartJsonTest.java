package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.CartEntity;
import com.store.core.CustomerCartEntity;
import com.store.core.CustomerEntity;
import com.store.core.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerCartJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();
  private CartEntity cart;
  private ProductEntity product;
  private CustomerEntity customer;
  private CustomerCartEntity customerCart;

  @BeforeEach
  void setUp() {
    customer = new CustomerEntity();
    customer.setId(1L);
    customer.setCustomerFirstName("Mary");
    customer.setCustomerLastName("Mack");
    customer.setCustomerAddressOne("3245 Baldwin");
    customer.setCustomerAddressTwo("St");
    customer.setCustomerCity("Pomona");
    customer.setCustomerState("CA");
    customer.setCustomerZipCode("91123");
    customer.setCustomerPhoneNumber("9097738978");
    customer.setCustomerEmail("mack@gmail.com");

    product = new ProductEntity();
    product.setId(1L);
    product.setProductName("Cupcake cups");
    product.setProductType("Cakes");
    product.setProductPrice(5.95);
    product.setProductDescription("Made out of baking sheets");
    product.setProductQuantity(100);

    cart = new CartEntity();
    cart.setId(1);
    cart.setProduct(product);
    cart.setCartQuantity(1);

    customerCart = new CustomerCartEntity();
    customerCart.setCustomer(customer);
    customerCart.setCart(cart);
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(getClass().getResource("/api/customer_carts.json"),
          CustomerCartEntity.class));

    assertThat(MAPPER.writeValueAsString(customerCart)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final long customerId = 1L;
    final long cartId = 1L;

    final CustomerCartEntity customerCartJson = MAPPER.readValue(getClass()
        .getResource("/api/customer_carts.json"), CustomerCartEntity.class);

    assertThat(customerCartJson.getCustomer().getId()).isEqualTo(customerId);
    assertThat(customerCartJson.getCart().getId()).isEqualTo(cartId);
  }

}
