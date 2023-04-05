package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.ProductEntity;
import com.store.pagination.PageTemplate;
import com.store.pagination.Pages;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {

  private ProductDaoRepository productDaoRepository;
  private PageTemplate pageTemplate;
  private final int pageNumber = 1;
  private final int pageSize = 2;

  @BeforeEach
  void setup() {
    productDaoRepository = new ProductDaoRepository(daoTest.getSessionFactory());
    pageTemplate = new PageTemplate();
    pageTemplate.setPageNumber(pageNumber);
    pageTemplate.setPageSize(pageSize);
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newProduct();
    }
    final List<Long> idList = new ArrayList<>();
    for (ProductEntity entity : productDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newProduct();

    final ProductEntity productEntity = productDaoRepository.getById(id).get();

    assertThat(productEntity.getProductName()).isNotNull();
    assertThat(productEntity.getProductPrice()).isNotNull();
    assertThat(productEntity.getProductDescription()).isNotNull();
    assertThat(productEntity.getProductQuantity()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newProduct();

    daoTest.inTransaction(() -> productDaoRepository.deleteById(id));

    assertThat(productDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newProduct();

    final ProductEntity productEntity = productDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> productDaoRepository.delete(productEntity));

    assertThat(productDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  @Test
  void testUpdate() {
    final long id = newProduct();
    final String productDescription = "New Description";
    final ProductEntity newProductEntity = productDaoRepository.getById(id).get();
    newProductEntity.setProductDescription(productDescription);

    assertThat(newProductEntity.getProductDescription()).isEqualTo(productDescription);
  }

  @Test
  void testPaginationPages() {
    final int nextPage = 2;
    final int prevPage = 1;
    final int lastPage = 3;

    final Pages pages = productDaoRepository.pagination(pageTemplate).getPages();

    assertThat(pages.getNextPage()).isEqualTo(nextPage);
    assertThat(pages.getPrevPage()).isEqualTo(prevPage);
    assertThat(pages.getLastPage()).isEqualTo(lastPage);
  }

  @Test
  void testPaginationList() {
    final List<ProductEntity> list = productDaoRepository.pagination(pageTemplate).getList();

    assertThat(pageSize).isEqualTo(list.size());
  }

  private long newProduct() {
    final ProductEntity productEntity = daoTest
        .inTransaction(() -> EntityUtil.productTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return productEntity.getId();
  }
}
