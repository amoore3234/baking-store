package com.store.db;

import com.store.core.ShippingAddressEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the ShippingAddress repository class.
 */
public class ShippingAddressDaoRepository extends AbstractDaoRepository<ShippingAddressEntity> {

  public ShippingAddressDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
