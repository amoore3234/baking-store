package com.store.db;

import com.store.core.CartEntity;
import org.hibernate.SessionFactory;

public class CartDaoRepository extends AbstractDaoRepository<CartEntity> {

  protected CartDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
