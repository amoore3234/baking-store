package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.CustomerEntity;
import com.store.core.OrderDetailEntity;
import com.store.core.ShippingAddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderDetailJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();
  private OrderDetailEntity orderDetail;
  private CustomerEntity customer;
  private ShippingAddressEntity shippingAddress;

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
    orderDetail.setCustomer(customer);
    orderDetail.setShippingAddress(shippingAddress);
    orderDetail.setPaymentType("Visa");
    orderDetail.setOrderDetailTotal(1);
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(MAPPER.readValue(getClass()
        .getResource("/api/order_details.json"), OrderDetailEntity.class));

    assertThat(MAPPER.writeValueAsString(orderDetail)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final long customerId = 1L;
    final long shippingAddressId = 1L;
    final String paymentType = "Visa";
    final int orderDetailTotal = 1;

    final OrderDetailEntity orderDetailJson = MAPPER.readValue(getClass()
        .getResource("/api/order_details.json"), OrderDetailEntity.class);

    assertThat(orderDetailJson.getCustomer().getId()).isEqualTo(customerId);
    assertThat(orderDetailJson.getShippingAddress().getId()).isEqualTo(shippingAddressId);
    assertThat(orderDetailJson.getPaymentType()).isEqualTo(paymentType);
    assertThat(orderDetailJson.getOrderDetailTotal()).isEqualTo(orderDetailTotal);
  }
}
