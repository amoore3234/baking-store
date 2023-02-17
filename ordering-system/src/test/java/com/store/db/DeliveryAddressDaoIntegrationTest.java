package com.store.db;

import static org.assertj.core.api.Assertions.assertThat;

import com.store.core.DeliveryAddressEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryAddressDaoIntegrationTest extends AbstractDaoRepositoryIntegrationTest {

  DeliveryAddressDaoRepository deliveryAddressDaoRepository;

  @BeforeEach
  void setup() {
    deliveryAddressDaoRepository = new DeliveryAddressDaoRepository(daoTest.getSessionFactory());
  }

  @Test
  void testFindAll() {
    final int count = 3;
    final Long[] ids = new Long[count];
    for (int i = 0; i < count; i++) {
      ids[i] = newDeliveryAddress();
    }
    final List<Long> idList = new ArrayList<>();
    for (DeliveryAddressEntity entity : deliveryAddressDaoRepository.findAll()) {
      idList.add(entity.getId());
    }
    assertThat(idList).hasSizeGreaterThanOrEqualTo(count).contains(ids);
  }

  @Test
  void testGeyById() {
    final long id = newDeliveryAddress();

    final DeliveryAddressEntity deliveryAddressEntity = deliveryAddressDaoRepository.getById(id).get();

    assertThat(deliveryAddressEntity.getDeliveryAddressOne()).isNotNull();
    assertThat(deliveryAddressEntity.getDeliveryAddressTwo()).isNotNull();
    assertThat(deliveryAddressEntity.getDeliveryAddressCity()).isNotNull();
    assertThat(deliveryAddressEntity.getDeliveryAddressState()).isNotNull();
    assertThat(deliveryAddressEntity.getDeliveryAddressEmail()).isNotNull();
    assertThat(deliveryAddressEntity.getDeliveryAddressZipCode()).isNotNull();
    assertThat(deliveryAddressEntity.getDeliveryAddressPhoneNumber()).isNotNull();
  }

  @Test
  void testDeleteById() {
    final long id = newDeliveryAddress();

    daoTest.inTransaction(() -> deliveryAddressDaoRepository.deleteById(id));

    assertThat(deliveryAddressDaoRepository.getById(id)).isEmpty();
  }

  @Test
  void testDeleteByEntity() {
    final long id = newDeliveryAddress();

    final DeliveryAddressEntity deliveryAddressEntity = deliveryAddressDaoRepository.getById(id).get();
    daoTest.inTransaction(() -> deliveryAddressDaoRepository.delete(deliveryAddressEntity));

    assertThat(deliveryAddressDaoRepository.getById(id).isPresent()).isEqualTo(false);
  }

  @Test
  void testUpdate() {
    final long id = newDeliveryAddress();
    final String newDeliveryAddress = "New Address Test";
    final DeliveryAddressEntity newDeliveryAddressEntity = deliveryAddressDaoRepository.getById(id).get();
    newDeliveryAddressEntity.setDeliveryAddressOne(newDeliveryAddress);

    assertThat(newDeliveryAddressEntity.getDeliveryAddressOne()).isEqualTo(newDeliveryAddress);
  }

  private long newDeliveryAddress() {
    final DeliveryAddressEntity deliveryAddressEntity = daoTest
        .inTransaction(() -> EntityUtil.deliveryAddressTestEntity(daoTest.getSessionFactory()));
    daoTest.getSessionFactory().getCurrentSession().clear();
    return deliveryAddressEntity.getId();
  }
}
