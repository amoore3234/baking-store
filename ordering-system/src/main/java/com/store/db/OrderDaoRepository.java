package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.OrderEntity;

public class OrderDaoRepository extends AbstractDaoRepository<OrderEntity> {

  protected OrderDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
