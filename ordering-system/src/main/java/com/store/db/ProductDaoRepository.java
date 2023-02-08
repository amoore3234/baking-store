package com.store.db;

import org.hibernate.SessionFactory;

import com.store.core.ProductEntity;

public class ProductDaoRepository extends AbstractDaoRepository<ProductEntity> {

  protected ProductDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
