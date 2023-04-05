package com.store.db;

import com.store.core.CartEntity;
import com.store.core.CustomerCartEntity;
import com.store.core.CustomerEntity;
import com.store.core.OrderDetailEntity;
import com.store.core.OrderEntity;
import com.store.core.ProductEntity;
import com.store.core.ShippingAddressEntity;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.zonky.test.db.postgres.embedded.LiquibasePreparer;
import io.zonky.test.db.postgres.embedded.PreparedDbProvider;
import java.sql.SQLException;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
class AbstractDaoRepositoryIntegrationTest {

  private final PreparedDbProvider preparedDbProvider = PreparedDbProvider
      .forPreparer(LiquibasePreparer.forClasspathLocation("migrations.xml"));

  protected final DAOTestExtension daoTest;

  /**
   * Initializes a postgres database for testing.
   */
  public AbstractDaoRepositoryIntegrationTest() {
    String jdbcUrl;
    try {
      jdbcUrl = preparedDbProvider.createDatabase();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    daoTest = DAOTestExtension.newBuilder()
        .customizeConfiguration(
            c -> c.setProperty(AvailableSettings.DIALECT, PostgreSQL10Dialect.class.getName()))
        .setDriver("org.postgresql.Driver")
        .setUrl(jdbcUrl)
        .setHbm2DdlAuto("none")
        .addEntityClass(CartEntity.class).addEntityClass(CustomerCartEntity.class)
        .addEntityClass(CustomerEntity.class).addEntityClass(ShippingAddressEntity.class)
        .addEntityClass(OrderEntity.class).addEntityClass(OrderDetailEntity.class)
        .addEntityClass(ProductEntity.class).build();
  }

}
