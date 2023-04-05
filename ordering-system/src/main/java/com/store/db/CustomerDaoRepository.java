package com.store.db;

import com.store.core.CustomerEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the Customer repository class.
 */
public class CustomerDaoRepository extends AbstractDaoRepository<CustomerEntity> {

  public CustomerDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
