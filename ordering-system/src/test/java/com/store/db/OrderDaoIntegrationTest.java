package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.OrderEntity;
import com.store.pagination.PageTemplate;
import com.store.pagination.Pages;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {

  private OrderDaoRepository orderDaoRepository;
  private PageTemplate pageTemplate;
  private final int pageNumber = 1;
  private final int pageSize = 2;

  @BeforeEach
  void setup() {
    orderDaoRepository = new OrderDaoRepository(daoTest.getSessionFactory());
    pageTemplate = new PageTemplate();
    pageTemplate.setPageNumber(pageNumber);
    pageTemplate.setPageSize(pageSize);
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newOrder();
    }
    final List<Long> idList = new ArrayList<>();
    for (OrderEntity entity : orderDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newOrder();

    final OrderEntity orderEntity = orderDaoRepository.getById(id).get();

    assertThat(orderEntity.getOrderDetail()).isNotNull();
    assertThat(orderEntity.getProduct()).isNotNull();
    assertThat(orderEntity.getOrderDate()).isNotNull();
    assertThat(orderEntity.getOrderTotal()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newOrder();

    daoTest.inTransaction(() -> orderDaoRepository.deleteById(id));

    assertThat(orderDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newOrder();

    final OrderEntity orderEntity = orderDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> orderDaoRepository.delete(orderEntity));

    assertThat(orderDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  @Test
  void testUpdate() {
    final long id = newOrder();
    final double orderTotal = 10.00;
    final OrderEntity newOrderEntity = orderDaoRepository.getById(id).get();
    newOrderEntity.setOrderTotal(orderTotal);

    assertThat(newOrderEntity.getOrderTotal()).isEqualTo(orderTotal);
  }

  @Test
  void testPaginationPages() {
    final int nextPage = 2;
    final int prevPage = 1;
    final int lastPage = 3;

    final Pages pages = orderDaoRepository.pagination(pageTemplate).getPages();

    assertThat(pages.getNextPage()).isEqualTo(nextPage);
    assertThat(pages.getPrevPage()).isEqualTo(prevPage);
    assertThat(pages.getLastPage()).isEqualTo(lastPage);
  }

  @Test
  void testPaginationList() {
    final List<OrderEntity> list = orderDaoRepository
        .pagination(pageTemplate).getList();

    assertThat(pageSize).isEqualTo(list.size());
  }

  private long newOrder() {
    final OrderEntity orderEntity = daoTest
        .inTransaction(() -> EntityUtil.orderTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return orderEntity.getId();
  }
}
