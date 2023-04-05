package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.ShippingAddressEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShippingAddressJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();
  private ShippingAddressEntity shippingAddress;

  @BeforeEach
  void setUp() {
    shippingAddress = new ShippingAddressEntity();
    shippingAddress.setShippingAddressName("Bright Lighting");
    shippingAddress.setShippingAddressOne("34677 Stockton Street");
    shippingAddress.setShippingAddressTwo("Suite 213");
    shippingAddress.setShippingAddressCity("Upland");
    shippingAddress.setShippingAddressState("CA");
    shippingAddress.setShippingAddressZipCode("91763");
    shippingAddress.setShippingAddressPhoneNumber("9096758894");
    shippingAddress.setShippingAddressEmail("shirley.shipley@blighting.com");
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(MAPPER.readValue(getClass()
        .getResource("/api/shipping_address.json"), ShippingAddressEntity.class));

    assertThat(MAPPER.writeValueAsString(shippingAddress)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final String name = "Bright Lighting";
    final String addressOne = "34677 Stockton Street";
    final String addressTwo = "Suite 213";
    final String city = "Upland";
    final String state = "CA";
    final String zipCode = "91763";
    final String phoneNumber = "9096758894";
    final String email = "shirley.shipley@blighting.com";

    final ShippingAddressEntity shippingAddressJson = MAPPER.readValue(getClass()
        .getResource("/api/shipping_address.json"), ShippingAddressEntity.class);

    assertThat(shippingAddressJson.getShippingAddressName()).isEqualTo(name);
    assertThat(shippingAddressJson.getShippingAddressOne()).isEqualTo(addressOne);
    assertThat(shippingAddressJson.getShippingAddressTwo()).isEqualTo(addressTwo);
    assertThat(shippingAddressJson.getShippingAddressCity()).isEqualTo(city);
    assertThat(shippingAddressJson.getShippingAddressState()).isEqualTo(state);
    assertThat(shippingAddressJson.getShippingAddressZipCode()).isEqualTo(zipCode);
    assertThat(shippingAddressJson.getShippingAddressPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(shippingAddressJson.getShippingAddressEmail()).isEqualTo(email);
  }
}
