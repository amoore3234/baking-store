package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.CustomerEntity;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CustomerEntityResourceTest extends AbstractResourceTest {

  private CustomerEntity entity;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new CustomerEntity();
    entity.setId(1);
    entity.setCustomerFirstName("test first name");
    entity.setCustomerLastName("test last name");
    entity.setCustomerAddressOne("test address one");
    entity.setCustomerAddressTwo("test address two");
    entity.setCustomerCity("test city");
    entity.setCustomerZipCode("00000");
    entity.setCustomerState("CA");
    entity.setCustomerPhoneNumber("000-000-0000");
    entity.setCustomerEmail("test email");
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(customerDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<CustomerEntity> entityList = new ArrayList<>();
    Mockito.when(customerDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/customers/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testSaveCustomer() {
    Mockito.when(customerDaoRepository.save(Mockito.any(CustomerEntity.class))).thenReturn(entity);
    Response response = extension.target("/customers/add-customer").request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(CustomerEntity.class).getCustomerFirstName())
        .isEqualTo(entity.getCustomerFirstName());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerLastName())
        .isEqualTo(entity.getCustomerLastName());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerAddressOne())
        .isEqualTo(entity.getCustomerAddressOne());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerAddressTwo())
        .isEqualTo(entity.getCustomerAddressTwo());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerCity())
        .isEqualTo(entity.getCustomerCity());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerZipCode())
        .isEqualTo(entity.getCustomerZipCode());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerState())
        .isEqualTo(entity.getCustomerState());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerPhoneNumber())
        .isEqualTo(entity.getCustomerPhoneNumber());
    assertThat(response.readEntity(CustomerEntity.class).getCustomerEmail())
        .isEqualTo(entity.getCustomerEmail());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testUpdateCustomer() {
    final String firstName = "update first name";
    final String lastName = "update last name";
    final String addressOne = "update address one";
    final String addressTwo = "update address two";
    final String city = "update city";
    final String state = "AZ";
    final String zipCode = "11111";
    final String phoneNumber = "111-111-1111";
    final String email = "update email";

    entity.setCustomerFirstName(firstName);
    entity.setCustomerLastName(lastName);
    entity.setCustomerAddressOne(addressOne);
    entity.setCustomerAddressTwo(addressTwo);
    entity.setCustomerCity(city);
    entity.setCustomerState(state);
    entity.setCustomerZipCode(zipCode);
    entity.setCustomerPhoneNumber(phoneNumber);
    entity.setCustomerEmail(email);

    Mockito.when(customerDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/customers/1").request().put(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(CustomerEntity.class).getCustomerFirstName()).isEqualTo(firstName);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerLastName()).isEqualTo(lastName);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerAddressOne()).isEqualTo(addressOne);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerAddressTwo()).isEqualTo(addressTwo);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerCity()).isEqualTo(city);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerState()).isEqualTo(state);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerZipCode()).isEqualTo(zipCode);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(response.readEntity(CustomerEntity.class).getCustomerEmail()).isEqualTo(email);
  }

  @Test
  void testFindById() {
    Mockito.when(customerDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    CustomerEntity response = extension.target("/customers/1").request().get(CustomerEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(customerDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(customerDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/customers/1").request().delete();

    assertThat(response.readEntity(CustomerEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
