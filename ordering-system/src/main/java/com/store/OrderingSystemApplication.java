package com.store;

import com.store.core.CartEntity;
import com.store.core.CustomerCartEntity;
import com.store.core.CustomerEntity;
import com.store.core.DeliveryAddressEntity;
import com.store.core.OrderDetailEntity;
import com.store.core.OrderEntity;
import com.store.core.ProductEntity;
import com.store.db.CustomerDaoRepository;
import com.store.health.AppHealthCheck;
import com.store.resource.CustomerEntityResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.hibernate.HibernateBundle;

public class OrderingSystemApplication extends Application<OrderingSystemConfiguration> {

  public static void main(final String[] args) throws Exception {
    new OrderingSystemApplication().run(args);
  }

  @Override
  public String getName() {
    return "OrderingSystem";
  }

    private final HibernateBundle<OrderingSystemConfiguration> hibernate = new HibernateBundle<OrderingSystemConfiguration>(
            CustomerEntity.class, DeliveryAddressEntity.class, OrderEntity.class,
            OrderDetailEntity.class, ProductEntity.class, CartEntity.class, CustomerCartEntity.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(OrderingSystemConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<OrderingSystemConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<OrderingSystemConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(OrderingSystemConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final OrderingSystemConfiguration configuration,
            final Environment environment) {
        final AppHealthCheck healthCheck = new AppHealthCheck(configuration.getTemplate());

        CustomerDaoRepository customerDaoRepository = new CustomerDaoRepository(hibernate.getSessionFactory());
        environment.jersey().register(new CustomerEntityResource(customerDaoRepository));
        environment.healthChecks().register("template", healthCheck);

    }

}
