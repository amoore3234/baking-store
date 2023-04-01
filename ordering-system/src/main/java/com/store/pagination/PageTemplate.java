package com.store.pagination;

import java.util.Objects;

public class PageTemplate {

  private int pageNumber;
  private int pageSize;

  public PageTemplate() {}

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageNumber, pageSize);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof PageTemplate)) {
      return false;
    }
    PageTemplate other = (PageTemplate) obj;
    return pageNumber == other.pageNumber && pageSize == other.pageSize;
  }

  @Override
  public String toString() {
    return "PageTemplate [pageNumber=" + pageNumber + ", pageSize=" + pageSize + "]";
  }

}
