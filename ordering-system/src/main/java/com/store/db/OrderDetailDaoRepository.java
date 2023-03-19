package com.store.db;

import com.store.core.OrderDetailEntity;
import org.hibernate.SessionFactory;

public class OrderDetailDaoRepository extends AbstractDaoRepository<OrderDetailEntity> {

  public OrderDetailDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
