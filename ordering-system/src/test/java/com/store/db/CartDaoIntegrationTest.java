package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.CartEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {
  private CartDaoRepository cartDaoRepository;

  @BeforeEach
  void setup() {
    cartDaoRepository = new CartDaoRepository(daoTest.getSessionFactory());
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newCart();
    }
    final List<Long> idList = new ArrayList<>();
    for (CartEntity entity : cartDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newCart();

    final CartEntity customerEntity = cartDaoRepository.getById(id).get();

    assertThat(customerEntity.getProduct()).isNotNull();
    assertThat(customerEntity.getCartQuantity()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newCart();

    daoTest.inTransaction(() -> cartDaoRepository.deleteById(id));

    assertThat(cartDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newCart();

    final CartEntity customerEntity = cartDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> cartDaoRepository.delete(customerEntity));

    assertThat(cartDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  private long newCart() {
    final CartEntity customerEntity = daoTest
        .inTransaction(() -> EntityUtil.cartTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return customerEntity.getId();
  }
}
