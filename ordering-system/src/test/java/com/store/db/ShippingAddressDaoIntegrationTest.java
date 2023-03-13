package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.ShippingAddressEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShippingAddressDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {

  private ShippingAddressDaoRepository shippingAddressDaoRepository;

  @BeforeEach
  void setup() {
    shippingAddressDaoRepository = new ShippingAddressDaoRepository(daoTest.getSessionFactory());
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newShippingAddress();
    }
    final List<Long> idList = new ArrayList<>();
    for (ShippingAddressEntity entity : shippingAddressDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newShippingAddress();

    final ShippingAddressEntity shippingAddressEntity =
        shippingAddressDaoRepository.getById(id).get();

    assertThat(shippingAddressEntity.getShippingAddressOne()).isNotNull();
    assertThat(shippingAddressEntity.getShippingAddressTwo()).isNotNull();
    assertThat(shippingAddressEntity.getShippingAddressCity()).isNotNull();
    assertThat(shippingAddressEntity.getShippingAddressState()).isNotNull();
    assertThat(shippingAddressEntity.getShippingAddressEmail()).isNotNull();
    assertThat(shippingAddressEntity.getShippingAddressZipCode()).isNotNull();
    assertThat(shippingAddressEntity.getShippingAddressPhoneNumber()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newShippingAddress();

    daoTest.inTransaction(() -> shippingAddressDaoRepository.deleteById(id));

    assertThat(shippingAddressDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newShippingAddress();

    final ShippingAddressEntity shippingAddressEntity =
          shippingAddressDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> shippingAddressDaoRepository.delete(shippingAddressEntity));

    assertThat(shippingAddressDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  @Test
  void testUpdate() {
    final long id = newShippingAddress();
    final String newShippingAddress = "New Address Test";
    final ShippingAddressEntity newShippingAddressEntity =
          shippingAddressDaoRepository.getById(id).get();
    newShippingAddressEntity.setShippingAddressOne(newShippingAddress);

    assertThat(newShippingAddressEntity.getShippingAddressOne()).isEqualTo(newShippingAddress);
  }

  private long newShippingAddress() {
    final ShippingAddressEntity shippingAddressEntity = daoTest
        .inTransaction(() -> EntityUtil.shippingAddressTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return shippingAddressEntity.getId();
  }
}
