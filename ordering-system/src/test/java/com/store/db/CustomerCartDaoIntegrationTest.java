package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.CustomerCartEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerCartDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {
  private CustomerCartDaoRepository customerCartDaoRepository;

  @BeforeEach
  void setup() {
    customerCartDaoRepository = new CustomerCartDaoRepository(daoTest.getSessionFactory());
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newCustomerCart();
    }
    final List<Long> idList = new ArrayList<>();
    for (CustomerCartEntity entity : customerCartDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newCustomerCart();

    final CustomerCartEntity customerCartEntity = customerCartDaoRepository.getById(id).get();

    assertThat(customerCartEntity.getCustomer()).isNotNull();
    assertThat(customerCartEntity.getCart()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newCustomerCart();

    daoTest.inTransaction(() -> customerCartDaoRepository.deleteById(id));

    assertThat(customerCartDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newCustomerCart();

    final CustomerCartEntity customerCartEntity = customerCartDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> customerCartDaoRepository.delete(customerCartEntity));

    assertThat(customerCartDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  private long newCustomerCart() {
    final CustomerCartEntity customerCartEntity = daoTest
        .inTransaction(() -> EntityUtil.customerCartTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return customerCartEntity.getId();
  }
}
