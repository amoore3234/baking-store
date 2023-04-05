package com.store.db;

import com.store.core.CustomerCartEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the CustomerCart repository class.
 */
public class CustomerCartDaoRepository extends AbstractDaoRepository<CustomerCartEntity> {

  public CustomerCartDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
