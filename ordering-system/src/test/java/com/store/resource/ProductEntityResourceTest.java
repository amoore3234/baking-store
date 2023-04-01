package com.store.resource;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.ProductEntity;
import com.store.pagination.PageTemplate;
import com.store.pagination.Pagination;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductEntityResourceTest extends AbstractResourceTest {

  private ProductEntity entity;
  private PageTemplate pageTemplate;
  private List<ProductEntity> list;
  private Pagination<ProductEntity> productPagination;
  private int statusCode;

  @BeforeEach
  void setUp() {
    statusCode = 200;
    entity = new ProductEntity();
    entity.setId(1);
    entity.setProductName("test product name");
    entity.setProductType("test product type");
    entity.setProductDescription("test product description");
    entity.setProductPrice(0.00);
    entity.setProductQuantity(1);

    productPagination = new Pagination<>();
    list = new ArrayList<>();
    list.add(entity);
    pageTemplate = new PageTemplate();
    pageTemplate.setPageNumber(1);
    pageTemplate.setPageSize(2);
    productPagination.setList(list);
  }

  @AfterEach
  void tearDown() {
    Mockito.reset(productDaoRepository);
  }

  @Test
  void testFindAll() {
    final int size = 1;
    List<ProductEntity> entityList = new ArrayList<>();
    Mockito.when(productDaoRepository.findAll()).thenReturn(entityList);
    Response response = extension.target("/products/find-all").request().get();

    assertThat(response.getLength()).isGreaterThanOrEqualTo(size);
  }

  @Test
  void testPagination() {
    final Pagination<ProductEntity> mockPagination = productDaoRepository.pagination(pageTemplate);
    Mockito.when(mockPagination).thenReturn(productPagination);
    Response response = extension.target("/products").queryParam("pageNumber", "1").request().get();

    assertThat(response.getStatus()).isEqualTo(statusCode);
  }

  @Test
  void testSaveCustomer() {
    Mockito.when(productDaoRepository.save(Mockito.any(ProductEntity.class))).thenReturn(entity);
    Response response = extension.target("/products/add-product")
        .request().post(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(ProductEntity.class).getProductName())
        .isEqualTo(entity.getProductName());
    assertThat(response.readEntity(ProductEntity.class).getProductType())
        .isEqualTo(entity.getProductType());
    assertThat(response.readEntity(ProductEntity.class).getProductDescription())
        .isEqualTo(entity.getProductDescription());
    assertThat(response.readEntity(ProductEntity.class).getProductPrice())
        .isEqualTo(entity.getProductPrice());
    assertThat(response.readEntity(ProductEntity.class).getProductQuantity())
        .isEqualTo(entity.getProductQuantity());
    assertThat(response.getStatus()).isEqualTo(statusCode);

  }

  @Test
  void testUpdateCustomer() {
    final String productName = "update product name";
    final String productType = "update product type";
    final String productDesc = "update product description";
    final double productPrice = 0.0;
    final int productQuantity = 1;

    entity.setProductName(productName);
    entity.setProductType(productType);
    entity.setProductDescription(productDesc);
    entity.setProductPrice(productPrice);
    entity.setProductQuantity(productQuantity);

    Mockito.when(productDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/products/1").request().put(Entity.json(entity));

    response.bufferEntity();

    assertThat(response.readEntity(ProductEntity.class).getProductName())
      .isEqualTo(productName);
    assertThat(response.readEntity(ProductEntity.class).getProductType())
      .isEqualTo(productType);
    assertThat(response.readEntity(ProductEntity.class).getProductDescription())
      .isEqualTo(productDesc);
    assertThat(response.readEntity(ProductEntity.class).getProductPrice())
      .isEqualTo(productPrice);
    assertThat(response.readEntity(ProductEntity.class).getProductQuantity()
      ).isEqualTo(productQuantity);

  }

  @Test
  void testFindById() {
    Mockito.when(productDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    ProductEntity response = extension.target("/products/1").request().get(ProductEntity.class);

    assertThat(response.getId()).isEqualTo(entity.getId());
    Mockito.verify(productDaoRepository).getById(1);
  }

  @Test
  void testDeleteById() {
    Mockito.when(productDaoRepository.getById(1L)).thenReturn(Optional.of(entity));
    Response response = extension.target("/products/1").request().delete();

    assertThat(response.readEntity(ProductEntity.class)).isNull();
    assertThat(response.getStatus()).isEqualTo(statusCode);
  }
}
