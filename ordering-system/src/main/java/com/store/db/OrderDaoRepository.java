package com.store.db;

import com.store.core.OrderEntity;
import org.hibernate.SessionFactory;

public class OrderDaoRepository extends AbstractDaoRepository<OrderEntity> {

  public OrderDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
