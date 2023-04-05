package com.store.pagination;

import jakarta.annotation.Nullable;
import java.util.List;

/**
 * A class that performs pagination functionality.
 */
public class Pagination<T> {

  @Nullable
  private Pages pages;

  @Nullable
  private List<T> list;

  public Pages getPages() {
    return pages;
  }

  public void setPages(Pages pages) {
    this.pages = pages;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

}
