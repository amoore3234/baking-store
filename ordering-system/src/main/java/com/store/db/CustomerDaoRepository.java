package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.CustomerCartEntity;

public class CustomerDaoRepository extends AbstractDaoRepository<CustomerCartEntity> {

  protected CustomerDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
