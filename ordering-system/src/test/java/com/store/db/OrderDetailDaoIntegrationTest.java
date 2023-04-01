package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.OrderDetailEntity;
import com.store.pagination.PageTemplate;
import com.store.pagination.Pages;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderDetailDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {

  private OrderDetailDaoRepository orderDetailDaoRepository;
  private PageTemplate pageTemplate;
  private final int pageNumber = 1;
  private final int pageSize = 2;

  @BeforeEach
  void setup() {
    orderDetailDaoRepository = new OrderDetailDaoRepository(daoTest.getSessionFactory());
    pageTemplate = new PageTemplate();
    pageTemplate.setPageNumber(pageNumber);
    pageTemplate.setPageSize(pageSize);
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newOrderDetail();
    }
    final List<Long> idList = new ArrayList<>();
    for (OrderDetailEntity entity : orderDetailDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newOrderDetail();

    final OrderDetailEntity orderDetailEntity = orderDetailDaoRepository.getById(id).get();

    assertThat(orderDetailEntity.getCustomer()).isNotNull();
    assertThat(orderDetailEntity.getShippingAddress()).isNotNull();
    assertThat(orderDetailEntity.getPaymentType()).isNotNull();
    assertThat(orderDetailEntity.getOrderDetailTotal()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newOrderDetail();

    daoTest.inTransaction(() -> orderDetailDaoRepository.deleteById(id));

    assertThat(orderDetailDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newOrderDetail();

    final OrderDetailEntity orderDetailEntity = orderDetailDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> orderDetailDaoRepository.delete(orderDetailEntity));

    assertThat(orderDetailDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  @Test
  void testUpdate() {
    final long id = newOrderDetail();
    final String paymentType = "New Test";
    final OrderDetailEntity newOrderDetailEntity = orderDetailDaoRepository.getById(id).get();
    newOrderDetailEntity.setPaymentType(paymentType);

    assertThat(newOrderDetailEntity.getPaymentType()).isEqualTo(paymentType);
  }

  @Test
  void testPaginationPages() {
    final int nextPage = 2;
    final int prevPage = 1;
    final int lastPage = 3;

    final Pages pages = orderDetailDaoRepository.pagination(pageTemplate).getPages();

    assertThat(pages.getNextPage()).isEqualTo(nextPage);
    assertThat(pages.getPrevPage()).isEqualTo(prevPage);
    assertThat(pages.getLastPage()).isEqualTo(lastPage);
  }

  @Test
  void testPaginationList() {
    final List<OrderDetailEntity> list = orderDetailDaoRepository
        .pagination(pageTemplate).getList();

    assertThat(pageSize).isEqualTo(list.size());
  }

  private long newOrderDetail() {
    final OrderDetailEntity orderDetailEntity = daoTest
        .inTransaction(() -> EntityUtil.orderDetailTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return orderDetailEntity.getId();
  }

}
