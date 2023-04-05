package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.OrderDetailEntity;
import com.store.core.OrderEntity;
import com.store.core.ProductEntity;
import com.store.pagination.PageTemplate;
import com.store.pagination.Pagination;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OrderEntityResourceTest extends AbstractResourceTest {

  private OrderEntity entity;
  private PageTemplate pageTemplate;
  private Pagination<OrderEntity> orderPagination;
  private List<OrderEntity> list;
  private ProductEntity productEntity;
  private OrderDetailEntity orderDetailIdEntity;
  final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new OrderEntity();
    productEntity = new ProductEntity();
    orderDetailIdEntity = new OrderDetailEntity();
    productEntity.setId(1);
    orderDetailIdEntity.setId(1);
    entity.setId(1);
    entity.setOrderDate(now);
    entity.setOrderTotal(1);
    entity.setProduct(productEntity);
    entity.setOrderDetail(orderDetailIdEntity);

    orderPagination = new Pagination<>();
    list = new ArrayList<>();
    list.add(entity);
    pageTemplate = new PageTemplate();
    pageTemplate.setPageNumber(1);
    pageTemplate.setPageSize(2);
    orderPagination.setList(list);
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(orderDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<OrderEntity> entityList = new ArrayList<>();
    Mockito.when(orderDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/orders/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testPagination() {
    final Pagination<OrderEntity> mockPagination = orderDaoRepository.pagination(pageTemplate);
    Mockito.when(mockPagination).thenReturn(orderPagination);
    Response response = extension.target("/orders").queryParam("pageNumber", "1").request().get();

    assertThat(response.getStatus()).isEqualTo(statusCode);
  }

  @Test
  void testSaveCustomer() {
    Mockito.when(orderDaoRepository.save(Mockito.any(OrderEntity.class))).thenReturn(entity);
    Response response = extension.target("/orders/add-order").request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(OrderEntity.class).getOrderDate())
        .isEqualTo(entity.getOrderDate());
    assertThat(response.readEntity(OrderEntity.class).getOrderTotal())
        .isEqualTo(entity.getOrderTotal());
    assertThat(response.readEntity(OrderEntity.class).getProduct().getId())
        .isEqualTo(entity.getProduct().getId());
    assertThat(response.readEntity(OrderEntity.class).getOrderDetail().getId())
        .isEqualTo(entity.getOrderDetail().getId());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testUpdateCustomer() {
    final OffsetDateTime orderDate = now;
    final int orderTotal = 1;
    final long productId = 2L;
    final long orderDetailId = 2L;

    productEntity.setId(productId);
    orderDetailIdEntity.setId(orderDetailId);

    entity.setOrderDate(orderDate);
    entity.setOrderTotal(orderTotal);
    entity.setProduct(productEntity);
    entity.setOrderDetail(orderDetailIdEntity);

    Mockito.when(orderDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/orders/1").request().put(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(OrderEntity.class).getOrderDate()).isEqualTo(orderDate);
    assertThat(response.readEntity(OrderEntity.class).getOrderTotal()).isEqualTo(orderTotal);
    assertThat(response.readEntity(OrderEntity.class).getProduct().getId()).isEqualTo(productId);
    assertThat(response.readEntity(OrderEntity.class).getOrderDetail().getId())
        .isEqualTo(orderDetailId);

  }

  @Test
  void testFindById() {
    Mockito.when(orderDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    OrderEntity response = extension.target("/orders/1").request().get(OrderEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(orderDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(orderDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/orders/1").request().delete();

    assertThat(response.readEntity(OrderEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
