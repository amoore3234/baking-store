package com.store.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * An application health check.
 */
public class AppHealthCheck extends HealthCheck {
  private final String template;

  public AppHealthCheck(String template) {
    this.template = template;
  }

  @Override
  protected Result check() throws Exception {
    final String saying = String.format(template, "TEST");
    if (!saying.contains("TEST")) {
      return Result.unhealthy("template doesn't include a name");
    }
    return Result.healthy();
  }
}
