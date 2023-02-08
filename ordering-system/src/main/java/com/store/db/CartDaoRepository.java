package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.CartEntity;

public class CartDaoRepository extends AbstractDaoRepository<CartEntity> {

  protected CartDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
