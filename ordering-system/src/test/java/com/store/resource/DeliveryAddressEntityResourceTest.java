package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.DeliveryAddressEntity;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeliveryAddressEntityResourceTest extends AbstractResourceTest {

  private DeliveryAddressEntity entity;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new DeliveryAddressEntity();
    entity.setId(1);
    entity.setDeliveryAddressName("test address name");
    entity.setDeliveryAddressOne("test address one");
    entity.setDeliveryAddressTwo("test address two");
    entity.setDeliveryAddressCity("test city");
    entity.setDeliveryAddressState("test state");
    entity.setDeliveryAddressZipCode("00000");
    entity.setDeliveryAddressPhoneNumber("000-000-0000");
    entity.setDeliveryAddressEmail("test email");
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(deliveryAddressDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<DeliveryAddressEntity> entityList = new ArrayList<>();
    Mockito.when(deliveryAddressDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/delivery-address/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testSaveDeliveryAddress() {
    Mockito.when(deliveryAddressDaoRepository.save(Mockito.any(DeliveryAddressEntity.class))).thenReturn(entity);
    Response response = extension.target("/delivery-address/add-delivery-address")
      .request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressName())
        .isEqualTo(entity.getDeliveryAddressName());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressOne())
        .isEqualTo(entity.getDeliveryAddressOne());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressTwo())
        .isEqualTo(entity.getDeliveryAddressTwo());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressCity())
        .isEqualTo(entity.getDeliveryAddressCity());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressState())
        .isEqualTo(entity.getDeliveryAddressState());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressZipCode())
        .isEqualTo(entity.getDeliveryAddressZipCode());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressPhoneNumber())
        .isEqualTo(entity.getDeliveryAddressPhoneNumber());
    assertThat(response.readEntity(DeliveryAddressEntity.class).getDeliveryAddressEmail())
        .isEqualTo(entity.getDeliveryAddressEmail());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testUpdateDeliveryAddress() {
    final String addressName = "update address name";
    final String addressOne = "update address one";
    final String addressTwo = "update address two";
    final String city = "update city";
    final String state = "AZ";
    final String zipCode = "11111";
    final String phoneNumber = "111-111-1111";
    final String email = "update email";

    entity.setDeliveryAddressName(addressName);
    entity.setDeliveryAddressOne(addressOne);
    entity.setDeliveryAddressTwo(addressTwo);
    entity.setDeliveryAddressCity(city);
    entity.setDeliveryAddressState(state);
    entity.setDeliveryAddressZipCode(zipCode);
    entity.setDeliveryAddressPhoneNumber(phoneNumber);
    entity.setDeliveryAddressEmail(email);

    Mockito.when(deliveryAddressDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/delivery-address/1").request().put(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressName()).isEqualTo(addressName);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressOne()).isEqualTo(addressOne);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressTwo()).isEqualTo(addressTwo);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressCity()).isEqualTo(city);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressState()).isEqualTo(state);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressZipCode()).isEqualTo(zipCode);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(response.readEntity(DeliveryAddressEntity.class)
      .getDeliveryAddressEmail()).isEqualTo(email);
  }

  @Test
  void testFindById() {
    Mockito.when(deliveryAddressDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    DeliveryAddressEntity response = extension.target("/delivery-address/1").request()
      .get(DeliveryAddressEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(deliveryAddressDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(deliveryAddressDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/delivery-address/1").request().delete();

    assertThat(response.readEntity(DeliveryAddressEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
