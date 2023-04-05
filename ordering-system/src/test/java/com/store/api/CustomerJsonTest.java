package com.store.api;

import static io.dropwizard.jackson.Jackson.newObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.core.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerJsonTest {

  private static final ObjectMapper MAPPER = newObjectMapper();
  private CustomerEntity customer;

  @BeforeEach
  void setUp() {
    customer = new CustomerEntity();
    customer.setCustomerFirstName("Mary");
    customer.setCustomerLastName("Mack");
    customer.setCustomerAddressOne("3245 Baldwin");
    customer.setCustomerAddressTwo("St");
    customer.setCustomerCity("Pomona");
    customer.setCustomerState("CA");
    customer.setCustomerZipCode("91123");
    customer.setCustomerPhoneNumber("9097738978");
    customer.setCustomerEmail("mack@gmail.com");
  }

  @Test
  void serializesToJson() throws Exception {

    final String expected = MAPPER.writeValueAsString(
        MAPPER.readValue(getClass().getResource("/api/customers.json"), CustomerEntity.class));

    assertThat(MAPPER.writeValueAsString(customer)).isEqualTo(expected);
  }

  @Test
  void deserializesFromJson() throws Exception {
    final String firstName = "Mary";
    final String lastName = "Mack";
    final String addressOne = "3245 Baldwin";
    final String addressTwo = "St";
    final String city = "Pomona";
    final String state = "CA";
    final String zipCode = "91123";
    final String phoneNumber = "9097738978";
    final String email = "mack@gmail.com";

    final CustomerEntity customerJson = MAPPER.readValue(getClass()
        .getResource("/api/customers.json"), CustomerEntity.class);

    assertThat(customerJson.getCustomerFirstName()).isEqualTo(firstName);
    assertThat(customerJson.getCustomerLastName()).isEqualTo(lastName);
    assertThat(customerJson.getCustomerAddressOne()).isEqualTo(addressOne);
    assertThat(customerJson.getCustomerAddressTwo()).isEqualTo(addressTwo);
    assertThat(customerJson.getCustomerCity()).isEqualTo(city);
    assertThat(customerJson.getCustomerState()).isEqualTo(state);
    assertThat(customerJson.getCustomerZipCode()).isEqualTo(zipCode);
    assertThat(customerJson.getCustomerPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(customerJson.getCustomerEmail()).isEqualTo(email);
  }
}
