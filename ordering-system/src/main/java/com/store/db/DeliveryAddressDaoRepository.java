package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.DeliveryAddressEntity;

public class DeliveryAddressDaoRepository extends AbstractDaoRepository<DeliveryAddressEntity> {

  protected DeliveryAddressDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
