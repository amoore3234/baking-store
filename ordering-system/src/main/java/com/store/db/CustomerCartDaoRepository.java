package com.store.db;

import com.store.core.CustomerCartEntity;
import org.hibernate.SessionFactory;

public class CustomerCartDaoRepository extends AbstractDaoRepository<CustomerCartEntity> {

  public CustomerCartDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
