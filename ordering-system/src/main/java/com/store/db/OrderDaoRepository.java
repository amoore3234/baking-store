package com.store.db;

import com.store.core.OrderEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the Order repository class.
 */
public class OrderDaoRepository extends AbstractDaoRepository<OrderEntity> {

  public OrderDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
