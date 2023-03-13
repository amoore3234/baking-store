package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.CustomerEntity;
import com.store.core.OrderDetailEntity;
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

public class OrderDetailEntityResourceTest extends AbstractResourceTest {

  private OrderDetailEntity entity;
  private CustomerEntity customerEntity;
  private ShippingAddressEntity shippingAddressEntity;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new OrderDetailEntity();
    customerEntity = new CustomerEntity();
    shippingAddressEntity = new ShippingAddressEntity();
    entity.setId(1L);
    customerEntity.setId(1L);
    shippingAddressEntity.setId(1L);
    entity.setCustomer(customerEntity);
    entity.setShippingAddress(shippingAddressEntity);
    entity.setPaymentType("test payment type");
    entity.setOrderDetailTotal(1);
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(orderDetailDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<OrderDetailEntity> entityList = new ArrayList<>();
    Mockito.when(orderDetailDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/order-details/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testSaveOrderDetail() {
    Mockito.when(orderDetailDaoRepository.save(Mockito.any(OrderDetailEntity.class)))
      .thenReturn(entity);
    Response response = extension.target("/order-details/add-order-detail")
        .request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(OrderDetailEntity.class).getCustomer().getId())
        .isEqualTo(entity.getCustomer().getId());
    assertThat(response.readEntity(OrderDetailEntity.class).getShippingAddress().getId())
        .isEqualTo(entity.getShippingAddress().getId());
    assertThat(response.readEntity(OrderDetailEntity.class).getPaymentType())
        .isEqualTo(entity.getPaymentType());
    assertThat(response.readEntity(OrderDetailEntity.class).getOrderDetailTotal())
        .isEqualTo(entity.getOrderDetailTotal());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testUpdateOrderDetail() {
    final long customerId = 2L;
    final long delveryAddressId = 3L;
    final String paymentType = "update payment type";
    final int orderDetailTotal = 3;

    customerEntity.setId(customerId);
    shippingAddressEntity.setId(delveryAddressId);

    entity.setCustomer(customerEntity);
    entity.setShippingAddress(shippingAddressEntity);
    entity.setPaymentType(paymentType);
    entity.setOrderDetailTotal(orderDetailTotal);

    Mockito.when(orderDetailDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/order-details/1").request().put(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(OrderDetailEntity.class)
      .getCustomer().getId()).isEqualTo(customerId);
    assertThat(response.readEntity(OrderDetailEntity.class)
      .getShippingAddress().getId()).isEqualTo(delveryAddressId);
    assertThat(response.readEntity(OrderDetailEntity.class)
      .getPaymentType()).isEqualTo(paymentType);
    assertThat(response.readEntity(OrderDetailEntity.class)
      .getOrderDetailTotal()).isEqualTo(orderDetailTotal);
  }

  @Test
  void testFindById() {
    Mockito.when(orderDetailDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    OrderDetailEntity response = extension.target("/order-details/1").request()
        .get(OrderDetailEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(orderDetailDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(orderDetailDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/order-details/1").request().delete();

    assertThat(response.readEntity(OrderDetailEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
