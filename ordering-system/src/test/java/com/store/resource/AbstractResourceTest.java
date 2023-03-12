package com.store.resource;

import com.store.db.CustomerDaoRepository;
import com.store.db.DeliveryAddressDaoRepository;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(DropwizardExtensionsSupport.class)
public abstract class AbstractResourceTest {

  final CustomerDaoRepository customerDaoRepository = Mockito.mock(CustomerDaoRepository.class);
  final DeliveryAddressDaoRepository deliveryAddressDaoRepository = Mockito.mock(DeliveryAddressDaoRepository.class);

  final ResourceExtension extension = ResourceExtension.builder()
      .addResource(new CustomerEntityResource(customerDaoRepository))
      .addResource(new DeliveryAddressEntityResource(deliveryAddressDaoRepository))
      .build();

  public AbstractResourceTest() {
  }

}
