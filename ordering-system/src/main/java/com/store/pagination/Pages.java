package com.store.pagination;

import java.util.Objects;

public class Pages {

  private int firstPage;
  private int lastPage;
  private int nextPage;
  private int prevPage;

  public Pages() {}

  public int getFirstPage() {
    return firstPage;
  }

  public void setFirstPage(int firstPage) {
    this.firstPage = firstPage;
  }

  public int getLastPage() {
    return lastPage;
  }

  public void setLastPage(int lastPage) {
    this.lastPage = lastPage;
  }

  public int getNextPage() {
    return nextPage;
  }

  public void setNextPage(int nextPage) {
    this.nextPage = nextPage;
  }

  public int getPrevPage() {
    return prevPage;
  }

  public void setPrevPage(int prevPage) {
    this.prevPage = prevPage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstPage, lastPage, nextPage, prevPage);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Pages)) {
      return false;
    }
    Pages other = (Pages) obj;
    return firstPage == other.firstPage && lastPage == other.lastPage
      && nextPage == other.nextPage
      && prevPage == other.prevPage;
  }

  @Override
  public String toString() {
    return "Pages [firstPage=" + firstPage + ", lastPage=" + lastPage
      + ", nextPage=" + nextPage + ", prevPage=" + prevPage + "]";
  }

}
