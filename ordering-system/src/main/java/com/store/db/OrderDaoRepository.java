package com.store.db;

import com.store.core.OrderEntity;
import org.hibernate.SessionFactory;

public class OrderDaoRepository extends AbstractDaoRepository<OrderEntity> {

  protected OrderDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
