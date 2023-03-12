package com.store.resource;

import com.store.db.CustomerDaoRepository;
import com.store.db.DeliveryAddressDaoRepository;
import com.store.db.OrderDaoRepository;
import com.store.db.OrderDetailDaoRepository;
import com.store.db.ProductDaoRepository;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(DropwizardExtensionsSupport.class)
public abstract class AbstractResourceTest {

  final CustomerDaoRepository customerDaoRepository = Mockito.mock(CustomerDaoRepository.class);
  final DeliveryAddressDaoRepository deliveryAddressDaoRepository = Mockito.mock(DeliveryAddressDaoRepository.class);
  final OrderDetailDaoRepository orderDetailDaoRepository = Mockito.mock(OrderDetailDaoRepository.class);
  final ProductDaoRepository productDaoRepository = Mockito.mock(ProductDaoRepository.class);
  final OrderDaoRepository orderDaoRepository = Mockito.mock(OrderDaoRepository.class);

  final ResourceExtension extension = ResourceExtension.builder()
      .addResource(new CustomerEntityResource(customerDaoRepository))
      .addResource(new DeliveryAddressEntityResource(deliveryAddressDaoRepository))
      .addResource(new OrderDetailEntityResource(orderDetailDaoRepository))
      .addResource(new ProductEntityResource(productDaoRepository))
      .addResource(new OrderEntityResource(orderDaoRepository))
      .build();

  public AbstractResourceTest() {
  }

}
