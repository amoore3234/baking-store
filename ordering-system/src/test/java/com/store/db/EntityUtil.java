package com.store.db;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.hibernate.SessionFactory;

import com.store.core.CartEntity;
import com.store.core.CustomerCartEntity;
import com.store.core.CustomerEntity;
import com.store.core.DeliveryAddressEntity;
import com.store.core.OrderDetailEntity;
import com.store.core.OrderEntity;
import com.store.core.ProductEntity;

class EntityUtil {

  private EntityUtil() {
  }

  static CustomerEntity customerTestEntity(SessionFactory sessionFactory) {
    final CustomerDaoRepository customerDaoRepository = new CustomerDaoRepository(sessionFactory);

    final CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setCustomerFirstName("test first name");
    customerEntity.setCustomerLastName("test last name");
    customerEntity.setCustomerAddressOne("test address one");
    customerEntity.setCustomerAddressTwo("test address two");
    customerEntity.setCustomerCity("test city");
    customerEntity.setCustomerZipCode("00000");
    customerEntity.setCustomerState("CA");
    customerEntity.setCustomerPhoneNumber("000-000-0000");
    customerEntity.setCustomerEmail("test email");
    customerDaoRepository.save(customerEntity);
    return customerEntity;
  }

  static DeliveryAddressEntity deliveryAddressTestEntity(SessionFactory sessionFactory) {
    final DeliveryAddressDaoRepository deliveryAddressDaoRepository = new DeliveryAddressDaoRepository(sessionFactory);

    final DeliveryAddressEntity deliveryAddressEntity = new DeliveryAddressEntity();
    deliveryAddressEntity.setDeliveryAddressOne("test address one");
    deliveryAddressEntity.setDeliveryAddressTwo("test address two");
    deliveryAddressEntity.setDeliveryAddressCity("test city");
    deliveryAddressEntity.setDeliveryAddressState("CA");
    deliveryAddressEntity.setDeliveryAddressPhoneNumber("000-000-0000");
    deliveryAddressEntity.setDeliveryAddressEmail("test email");
    deliveryAddressDaoRepository.save(deliveryAddressEntity);
    return deliveryAddressEntity;
  }

  static ProductEntity productTestEntity(SessionFactory sessionFactory) {
    final ProductDaoRepository productDaoRepository = new ProductDaoRepository(sessionFactory);

    final ProductEntity productEntity = new ProductEntity();
    productEntity.setProductName("test product name");
    productEntity.setProductPrice(2.0);
    productEntity.setProductDescription("Test description");
    productEntity.setProductQuantity(1);
    productDaoRepository.save(productEntity);
    return productEntity;

  }

  static OrderDetailEntity orderDetailTestEntity(SessionFactory sessionFactory) {
    final OrderDetailDaoRepository orderDetailDaoRepository = new OrderDetailDaoRepository(sessionFactory);

    final OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
    orderDetailEntity.setCustomer(customerTestEntity(sessionFactory));
    orderDetailEntity.setDeliveryAddress(deliveryAddressTestEntity(sessionFactory));
    orderDetailEntity.setPaymentType("test payment type");
    orderDetailEntity.setOrderDetailTotal(1);
    orderDetailDaoRepository.save(orderDetailEntity);
    return orderDetailEntity;
  }

  static OrderEntity orderTestEntity(SessionFactory sessionFactory) {
    final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    final OrderDaoRepository orderDaoRepository = new OrderDaoRepository(sessionFactory);

    final OrderEntity orderEntity = new OrderEntity();
    orderEntity.setDate(now);
    orderEntity.setProduct(productTestEntity(sessionFactory));
    orderEntity.setOrderTotal(0);
    orderDaoRepository.save(orderEntity);
    return orderEntity;
  }

  static CustomerCartEntity customerCartTestEntity(SessionFactory sessionFactory) {
    final CustomerCartDaoRepository customerCartDaoRepository = new CustomerCartDaoRepository(sessionFactory);

    final CustomerCartEntity customerCartEntity = new CustomerCartEntity();
    customerCartEntity.setCustomer(customerTestEntity(sessionFactory));
    customerCartEntity.setCart(cartTestEntity(sessionFactory));
    customerCartDaoRepository.save(customerCartEntity);
    return customerCartEntity;
  }

  static CartEntity cartTestEntity(SessionFactory sessionFactory) {
    final CartDaoRepository cartDaoRepository = new CartDaoRepository(sessionFactory);

    final CartEntity cartEntity = new CartEntity();
    cartEntity.setProduct(productTestEntity(sessionFactory));
    cartEntity.setCartQuantity(1);
    cartDaoRepository.save(cartEntity);
    return cartEntity;
  }
}
