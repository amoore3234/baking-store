package com.store.db;

import com.store.core.ProductEntity;
import org.hibernate.SessionFactory;

/**
 * Initializes a session factory for the Product repository class.
 */
public class ProductDaoRepository extends AbstractDaoRepository<ProductEntity> {

  public ProductDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
