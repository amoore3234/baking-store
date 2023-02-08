package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.CustomerCartEntity;

public class CustomerCartDaoRepository extends AbstractDaoRepository<CustomerCartEntity> {

  protected CustomerCartDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
