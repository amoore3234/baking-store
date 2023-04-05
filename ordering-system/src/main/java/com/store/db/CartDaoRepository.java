package com.store.db;

import com.store.core.CartEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the Cart repository class.
 */
public class CartDaoRepository extends AbstractDaoRepository<CartEntity> {

  public CartDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
