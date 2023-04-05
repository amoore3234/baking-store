package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.CartEntity;
import com.store.core.ProductEntity;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CartEntityResourceTest extends AbstractResourceTest {

  private CartEntity entity;
  private ProductEntity productEntity;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new CartEntity();
    productEntity = new ProductEntity();
    entity.setId(1L);
    productEntity.setId(1L);
    entity.setProduct(productEntity);
    entity.setCartQuantity(1);
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(cartDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<CartEntity> entityList = new ArrayList<>();
    Mockito.when(cartDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/carts/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testSaveCart() {
    Mockito.when(cartDaoRepository.save(Mockito.any(CartEntity.class))).thenReturn(entity);
    Response response = extension.target("/carts/add-cart")
        .request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(CartEntity.class).getProduct().getId())
        .isEqualTo(entity.getProduct().getId());
    assertThat(response.readEntity(CartEntity.class).getCartQuantity())
        .isEqualTo(entity.getCartQuantity());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testFindById() {
    Mockito.when(cartDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    CartEntity response = extension.target("/carts/1").request().get(CartEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(cartDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(cartDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/carts/1").request().delete();

    assertThat(response.readEntity(CartEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
