package com.store.db;

import com.store.pagination.PageTemplate;
import com.store.pagination.Pages;
import com.store.pagination.Pagination;
import io.dropwizard.hibernate.AbstractDAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

/**
 * Abstract repository class that holds CRUD and Dao methods.
 */
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

  /**
   * Method that performs paginated functionality.
   *
   * @param pageTemplate defines an object that includes a page number and page size.
   * @return returns a new pagination object that stores a paginated list and pages.
   */
  public Pagination<T> pagination(PageTemplate pageTemplate) {
    final CriteriaQuery<T> select = createSelect();
    final List<T> resultList = currentSession().createQuery(select).list();
    final TypedQuery<T> typedQuery = currentSession().createQuery(select);
    final Pages pages = getPages(pageTemplate, resultList);
    typedQuery.setFirstResult((pageTemplate.getPageNumber() - 1) * pageTemplate.getPageSize());
    typedQuery.setMaxResults(pageTemplate.getPageSize());
    final List<T> finalList = typedQuery.getResultList();
    Pagination<T> newPaginator = new Pagination<T>();
    newPaginator.setPages(pages);
    newPaginator.setList(finalList);
    return newPaginator;
  }

  private Pages getPages(PageTemplate pageTemplate, List<T> list) {

    final int totalCount = list.size();
    final int currentPageNumber = pageTemplate.getPageNumber();
    int pageSize = pageTemplate.getPageSize();

    Pages pages = new Pages();

    if (totalCount < pageSize) {
      pageSize = totalCount;
    }
    final int lastPage = totalCount / pageSize;
    pages.setFirstPage(1);
    pages.setLastPage(totalCount % pageSize == 0 ? lastPage : lastPage + 1);
    pages.setNextPage(currentPageNumber < pages.getLastPage()
        ? currentPageNumber + 1 : pages.getLastPage());
    pages.setPrevPage(currentPageNumber - 1 <= 0 ? 1 : currentPageNumber - 1);
    return pages;
  }

  private CriteriaQuery<T> createSelect() {
    final Class<T> clazz = getEntityClass();
    final CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
    final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
    final Root<T> root = criteriaQuery.from(clazz);
    final CriteriaQuery<T> select = criteriaQuery.select(root);
    return select;
  }
}
