package com.store.pagination;

import jakarta.annotation.Nullable;
import java.util.Objects;

/**
 * A class used for filtering products.
 */
public class Filter {

  @Nullable
  private String cakeFilter;

  @Nullable
  private String toolFilter;

  @Nullable
  private String topperFilter;

  public Filter() {}

  @Nullable
  public String getCakeFilter() {
    return cakeFilter;
  }

  public void setCakeFilter(@Nullable String cakeFilter) {
    this.cakeFilter = cakeFilter;
  }

  @Nullable
  public String getToolFilter() {
    return toolFilter;
  }

  public void setToolFilter(@Nullable String toolFilter) {
    this.toolFilter = toolFilter;
  }

  @Nullable
  public String getTopperFilter() {
    return topperFilter;
  }

  public void setTopperFilter(@Nullable String topperFilter) {
    this.topperFilter = topperFilter;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cakeFilter, toolFilter, topperFilter);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Filter)) {
      return false;
    }
    Filter other = (Filter) obj;
    return Objects.equals(cakeFilter, other.cakeFilter)
      && Objects.equals(toolFilter, other.toolFilter)
      && Objects.equals(topperFilter, other.topperFilter);
  }

  @Override
  public String toString() {
    return "Filter [cakeFilter=" + cakeFilter + ", toolFilter=" + toolFilter
      + ", topperFilter=" + topperFilter + "]";
  }

}
