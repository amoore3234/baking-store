package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.OrderDetailEntity;

public class OrderDetailDaoRepository extends AbstractDaoRepository<OrderDetailEntity> {

  protected OrderDetailDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
