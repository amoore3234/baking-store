package com.store;

import com.store.core.CartEntity;
import com.store.core.CustomerCartEntity;
import com.store.core.CustomerEntity;
import com.store.core.OrderDetailEntity;
import com.store.core.OrderEntity;
import com.store.core.ProductEntity;
import com.store.core.ShippingAddressEntity;
import com.store.db.CartDaoRepository;
import com.store.db.CustomerCartDaoRepository;
import com.store.db.CustomerDaoRepository;
import com.store.db.OrderDaoRepository;
import com.store.db.OrderDetailDaoRepository;
import com.store.db.ProductDaoRepository;
import com.store.db.ShippingAddressDaoRepository;
import com.store.health.AppHealthCheck;
import com.store.resource.CartEntityResource;
import com.store.resource.CustomerCartEntityResource;
import com.store.resource.CustomerEntityResource;
import com.store.resource.OrderDetailEntityResource;
import com.store.resource.OrderEntityResource;
import com.store.resource.ProductEntityResource;
import com.store.resource.ShippingAddressEntityResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;

public class OrderingSystemApplication extends Application<OrderingSystemConfiguration> {

  public static void main(final String[] args) throws Exception {
    new OrderingSystemApplication().run(args);
  }

  @Override
  public String getName() {
    return "OrderingSystem";
  }

  private final HibernateBundle<OrderingSystemConfiguration> hibernate =
      new HibernateBundle<OrderingSystemConfiguration>(
          CustomerEntity.class, ShippingAddressEntity.class, OrderEntity.class,
          OrderDetailEntity.class, ProductEntity.class, CartEntity.class,
          CustomerCartEntity.class) {

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

    CustomerDaoRepository customerDaoRepository =
        new CustomerDaoRepository(hibernate.getSessionFactory());
    ShippingAddressDaoRepository shippingAddressDaoRepository =
        new ShippingAddressDaoRepository(hibernate.getSessionFactory());
    OrderDetailDaoRepository orderDetailDaoRepository =
        new OrderDetailDaoRepository(hibernate.getSessionFactory());
    ProductDaoRepository productDaoRepository =
        new ProductDaoRepository(hibernate.getSessionFactory());
    OrderDaoRepository orderDaoRepository =
        new OrderDaoRepository(hibernate.getSessionFactory());
    CartDaoRepository cartDaoRepository =
        new CartDaoRepository(hibernate.getSessionFactory());
    CustomerCartDaoRepository customerCartDaoRepository =
        new CustomerCartDaoRepository(hibernate.getSessionFactory());

    environment.jersey().register(new CustomerEntityResource(customerDaoRepository));
    environment.jersey().register(new ShippingAddressEntityResource(shippingAddressDaoRepository));
    environment.jersey().register(new OrderDetailEntityResource(orderDetailDaoRepository));
    environment.jersey().register(new ProductEntityResource(productDaoRepository));
    environment.jersey().register(new OrderEntityResource(orderDaoRepository));
    environment.jersey().register(new CartEntityResource(cartDaoRepository));
    environment.jersey().register(new CustomerCartEntityResource(customerCartDaoRepository));
    environment.healthChecks().register("template", healthCheck);

  }

}
