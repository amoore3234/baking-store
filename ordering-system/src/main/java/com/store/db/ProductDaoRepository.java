package com.store.db;

import com.store.core.ProductEntity;
import org.hibernate.SessionFactory;

public class ProductDaoRepository extends AbstractDaoRepository<ProductEntity> {

  protected ProductDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
