package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.CartEntity;
import com.store.core.CustomerCartEntity;
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

class CustomerCartResourceTest extends AbstractResourceTest {

  private CustomerCartEntity entity;
  private CustomerEntity customerEntity;
  private CartEntity cartEntity;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new CustomerCartEntity();
    cartEntity = new CartEntity();
    customerEntity = new CustomerEntity();
    entity.setId(1L);
    cartEntity.setId(1L);
    customerEntity.setId(1L);
    entity.setCustomer(customerEntity);
    entity.setCart(cartEntity);
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(customerCartDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<CustomerCartEntity> entityList = new ArrayList<>();
    Mockito.when(customerCartDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/customer-carts/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testSaveCart() {
    Mockito.when(customerCartDaoRepository.save(Mockito.any(CustomerCartEntity.class)))
        .thenReturn(entity);
    Response response = extension.target("/customer-carts/add-customer-cart")
        .request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(CustomerCartEntity.class).getCustomer().getId())
        .isEqualTo(entity.getCustomer().getId());
    assertThat(response.readEntity(CustomerCartEntity.class).getCart().getId())
        .isEqualTo(entity.getCart().getId());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testFindById() {
    Mockito.when(customerCartDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    CustomerCartEntity response = extension.target("/customer-carts/1").request()
        .get(CustomerCartEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(customerCartDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(customerCartDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/customer-carts/1").request().delete();

    assertThat(response.readEntity(CustomerCartEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }

}
