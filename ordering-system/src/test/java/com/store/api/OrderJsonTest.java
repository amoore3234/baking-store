package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.CustomerEntity;
import com.store.core.OrderDetailEntity;
import com.store.core.OrderEntity;
import com.store.core.ProductEntity;
import com.store.core.ShippingAddressEntity;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();

  private OrderEntity order;
  private OrderDetailEntity orderDetail;
  private CustomerEntity customer;
  private ShippingAddressEntity shippingAddress;
  private ProductEntity product;

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

    shippingAddress = new ShippingAddressEntity();
    shippingAddress.setId(1L);
    shippingAddress.setShippingAddressName("Bright Lighting");
    shippingAddress.setShippingAddressOne("34677 Stockton Street");
    shippingAddress.setShippingAddressTwo("Suite 213");
    shippingAddress.setShippingAddressCity("Upland");
    shippingAddress.setShippingAddressState("CA");
    shippingAddress.setShippingAddressZipCode("91763");
    shippingAddress.setShippingAddressPhoneNumber("9096758894");
    shippingAddress.setShippingAddressEmail("shirley.shipley@blighting.com");

    orderDetail = new OrderDetailEntity();
    orderDetail.setId(1L);
    orderDetail.setCustomer(customer);
    orderDetail.setShippingAddress(shippingAddress);
    orderDetail.setPaymentType("Visa");
    orderDetail.setOrderDetailTotal(1);

    product = new ProductEntity();
    product.setId(1L);
    product.setProductName("Cupcake cups");
    product.setProductType("Cakes");
    product.setProductPrice(5.95);
    product.setProductDescription("Made out of baking sheets");
    product.setProductQuantity(100);

    order = new OrderEntity();
    order.setProduct(product);
    order.setOrderDetail(orderDetail);
    order.setOrderDate(LocalDateTime.of(2020, 07, 21, 10, 12, 23, 0).atOffset(ZoneOffset.UTC));
    order.setOrderTotal(5.95);
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(MAPPER.readValue(getClass()
        .getResource("/api/orders.json"), OrderEntity.class));

    assertThat(MAPPER.writeValueAsString(order)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final long productId = 1L;
    final long orderDetailId = 1L;
    final OffsetDateTime orderDate = LocalDateTime.of(2020, 07, 21, 10, 12, 23, 0).atOffset(ZoneOffset.UTC);
    final double orderTotal = 5.95;

    final OrderEntity orderJson = MAPPER.readValue(getClass()
        .getResource("/api/orders.json"), OrderEntity.class);

    assertThat(orderJson.getProduct().getId()).isEqualTo(productId);
    assertThat(orderJson.getOrderDetail().getId()).isEqualTo(orderDetailId);
    assertThat(orderJson.getOrderDate()).isEqualTo(orderDate);
    assertThat(orderJson.getOrderTotal()).isEqualTo(orderTotal);
  }
}
