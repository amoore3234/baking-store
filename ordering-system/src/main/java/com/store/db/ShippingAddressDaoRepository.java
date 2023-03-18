package com.store.db;

import com.store.core.ShippingAddressEntity;
import org.hibernate.SessionFactory;

public class ShippingAddressDaoRepository extends AbstractDaoRepository<ShippingAddressEntity> {

  public ShippingAddressDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
