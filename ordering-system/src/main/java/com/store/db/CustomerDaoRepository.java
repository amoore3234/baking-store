package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.CustomerEntity;

public class CustomerDaoRepository extends AbstractDaoRepository<CustomerEntity> {

  public CustomerDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
