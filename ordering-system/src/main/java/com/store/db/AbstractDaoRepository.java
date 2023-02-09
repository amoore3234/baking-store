package com.store.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public abstract class AbstractDaoRepository<T> extends AbstractDAO<T> {

  protected AbstractDaoRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public T save(T entity) {
    return persist(entity);
  }

  public List<T> findAll() {
    return list(query(String.format("from %s", getEntityClass().getSimpleName())));
  }

  public Optional<T> getById(long id) {
    return Optional.ofNullable(get(id));
  }

  public void deleteById(long id) {
    getById(id).ifPresent(this::delete);
  }

  public void delete(T entity) {
    currentSession().delete(entity);
  }
}
