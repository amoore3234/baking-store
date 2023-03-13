package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.ShippingAddressEntity;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ShippingAddressEntityResourceTest extends AbstractResourceTest {

  private ShippingAddressEntity entity;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new ShippingAddressEntity();
    entity.setId(1);
    entity.setShippingAddressName("test address name");
    entity.setShippingAddressOne("test address one");
    entity.setShippingAddressTwo("test address two");
    entity.setShippingAddressCity("test city");
    entity.setShippingAddressState("test state");
    entity.setShippingAddressZipCode("00000");
    entity.setShippingAddressPhoneNumber("000-000-0000");
    entity.setShippingAddressEmail("test email");
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(shippingAddressDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<ShippingAddressEntity> entityList = new ArrayList<>();
    Mockito.when(shippingAddressDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/shipping-address/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testSaveShippingAddress() {
    Mockito.when(shippingAddressDaoRepository.save(Mockito.any(ShippingAddressEntity.class)))
      .thenReturn(entity);
    Response response = extension.target("/shipping-address/add-shipping-address")
        .request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressName())
        .isEqualTo(entity.getShippingAddressName());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressOne())
        .isEqualTo(entity.getShippingAddressOne());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressTwo())
        .isEqualTo(entity.getShippingAddressTwo());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressCity())
        .isEqualTo(entity.getShippingAddressCity());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressState())
        .isEqualTo(entity.getShippingAddressState());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressZipCode())
        .isEqualTo(entity.getShippingAddressZipCode());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressPhoneNumber())
        .isEqualTo(entity.getShippingAddressPhoneNumber());
    assertThat(response.readEntity(ShippingAddressEntity.class).getShippingAddressEmail())
        .isEqualTo(entity.getShippingAddressEmail());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testUpdateShippingAddress() {
    final String addressName = "update address name";
    final String addressOne = "update address one";
    final String addressTwo = "update address two";
    final String city = "update city";
    final String state = "AZ";
    final String zipCode = "11111";
    final String phoneNumber = "111-111-1111";
    final String email = "update email";

    entity.setShippingAddressName(addressName);
    entity.setShippingAddressOne(addressOne);
    entity.setShippingAddressTwo(addressTwo);
    entity.setShippingAddressCity(city);
    entity.setShippingAddressState(state);
    entity.setShippingAddressZipCode(zipCode);
    entity.setShippingAddressPhoneNumber(phoneNumber);
    entity.setShippingAddressEmail(email);

    Mockito.when(shippingAddressDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/shipping-address/1").request().put(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressName()).isEqualTo(addressName);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressOne()).isEqualTo(addressOne);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressTwo()).isEqualTo(addressTwo);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressCity()).isEqualTo(city);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressState()).isEqualTo(state);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressZipCode()).isEqualTo(zipCode);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(response.readEntity(ShippingAddressEntity.class)
      .getShippingAddressEmail()).isEqualTo(email);
  }

  @Test
  void testFindById() {
    Mockito.when(shippingAddressDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    ShippingAddressEntity response = extension.target("/shipping-address/1").request()
        .get(ShippingAddressEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(shippingAddressDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(shippingAddressDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/shipping-address/1").request().delete();

    assertThat(response.readEntity(ShippingAddressEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
