package com.store.resource;

import com.store.db.CustomerDaoRepository;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(DropwizardExtensionsSupport.class)
public abstract class AbstractResourceTest {

  final CustomerDaoRepository customerDaoRepository = Mockito.mock(CustomerDaoRepository.class);

  final ResourceExtension extension = ResourceExtension.builder()
      .addResource(new CustomerEntityResource(customerDaoRepository))
      .build();

  public AbstractResourceTest() {
  }

}
