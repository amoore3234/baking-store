package com.store;

import com.store.health.AppHealthCheck;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;

public class OrderingSystemApplication extends Application<OrderingSystemConfiguration> {

    public static void main(final String[] args) throws Exception {
        new OrderingSystemApplication().run(args);
    }

    @Override
    public String getName() {
        return "OrderingSystem";
    }

    @Override
    public void initialize(final Bootstrap<OrderingSystemConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<OrderingSystemConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(OrderingSystemConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final OrderingSystemConfiguration configuration,
            final Environment environment) {
        final AppHealthCheck healthCheck = new AppHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
