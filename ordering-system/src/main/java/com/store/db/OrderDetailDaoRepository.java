package com.store.db;

import com.store.core.OrderDetailEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the OrderDetail repository class.
 */
public class OrderDetailDaoRepository extends AbstractDaoRepository<OrderDetailEntity> {

  public OrderDetailDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
