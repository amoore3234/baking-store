package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.store.core.CustomerEntity;

public class CustomerDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {

  CustomerDaoRepository customerDaoRepository;

  @BeforeEach
  void setup() {
    customerDaoRepository = new CustomerDaoRepository(daoTest.getSessionFactory());
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newCustomer();
    }
    final List<Long> idList = new ArrayList<>();
    for (CustomerEntity entity : customerDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newCustomer();

    final CustomerEntity customerEntity = customerDaoRepository.getById(id).get();

    assertThat(customerEntity.getCustomerFirstName()).isNotNull();
    assertThat(customerEntity.getCustomerLastName()).isNotNull();
    assertThat(customerEntity.getCustomerAddressOne()).isNotNull();
    assertThat(customerEntity.getCustomerAddressTwo()).isNotNull();
    assertThat(customerEntity.getCustomerCity()).isNotNull();
    assertThat(customerEntity.getCustomerState()).isNotNull();
    assertThat(customerEntity.getCustomerZipCode()).isNotNull();
    assertThat(customerEntity.getCustomerPhoneNumber()).isNotNull();
    assertThat(customerEntity.getCustomerEmail()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newCustomer();

    daoTest.inTransaction(() -> customerDaoRepository.deleteById(id));

    assertThat(customerDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newCustomer();

    final CustomerEntity customerEntity = customerDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> customerDaoRepository.delete(customerEntity));

    assertThat(customerDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  @Test
  void testUpdate() {
    final long id = newCustomer();
    final String newFirstName = "New Test";
    final CustomerEntity newCustomerEntity = customerDaoRepository.getById(id).get();
    newCustomerEntity.setCustomerFirstName(newFirstName);

    assertThat(newCustomerEntity.getCustomerFirstName()).isEqualTo(newFirstName);
  }

  private long newCustomer() {
    final CustomerEntity customerEntity = daoTest
        .inTransaction(() -> EntityUtil.customerTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return customerEntity.getId();
  }
}
