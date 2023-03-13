package com.store.db;

import com.store.core.CustomerEntity;
import org.hibernate.SessionFactory;

public class CustomerDaoRepository extends AbstractDaoRepository<CustomerEntity> {

  public CustomerDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
