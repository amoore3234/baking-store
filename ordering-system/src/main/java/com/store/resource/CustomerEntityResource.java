package com.store.resource;

import com.store.core.CustomerEntity;
import com.store.db.CustomerDaoRepository;
import com.store.exception.DataNotFoundException;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * A resource that includes CRUD and other endpoints for customers.
 */
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEntityResource {

  private final CustomerDaoRepository customerDaoRepository;

  public CustomerEntityResource(CustomerDaoRepository customerDaoRepository) {
    this.customerDaoRepository = customerDaoRepository;
  }

  /**
   * A Response that fetches all customers.
   *
   * @return a Response that returns a list of customers.
   */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<CustomerEntity> findAll = customerDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  /**
   * A Response that retrieves a customer by id.
   *
   * @param id defines a customer's id.
   * @return a Response that returns a customer object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    CustomerEntity customerEntity = customerDaoRepository.getById(id).orElseThrow(
        () -> {
        return new DataNotFoundException("Customer with id " + id + " does not exist");
      });

    return Response
          .ok(customerEntity)
          .build();
  }

  /**
   * A Response method that saves a customer.
   *
   * @param customerEntity {@link CustomerEntity} defines the CustomerEntity class.
   * @return a Response that returns a customer object.
   */
  @POST
  @UnitOfWork
  @Path("add-customer")
  public Response addCustomer(CustomerEntity customerEntity) {

    CustomerEntity addCustomer = customerDaoRepository.save(customerEntity);
    return Response
          .ok(addCustomer)
          .build();
  }

  /**
   * A Response method that updates a customer.
   *
   * @param id defines a customer's id.
   * @return a Response that returns a customer object.
   */
  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateCustomer(@PathParam("id") long id, CustomerEntity updateCustomer) {

    CustomerEntity getCustomer = customerDaoRepository.getById(id).orElseThrow(
        () -> {
        return new DataNotFoundException("Customer with id " + id + " does not exist");
      });

    getCustomer.setCustomerFirstName(updateCustomer.getCustomerFirstName());
    getCustomer.setCustomerLastName(updateCustomer.getCustomerLastName());
    getCustomer.setCustomerAddressOne(updateCustomer.getCustomerAddressOne());
    getCustomer.setCustomerAddressTwo(updateCustomer.getCustomerAddressTwo());
    getCustomer.setCustomerCity(updateCustomer.getCustomerCity());
    getCustomer.setCustomerState(updateCustomer.getCustomerState());
    getCustomer.setCustomerZipCode(updateCustomer.getCustomerZipCode());
    getCustomer.setCustomerEmail(updateCustomer.getCustomerEmail());
    getCustomer.setCustomerPhoneNumber(updateCustomer.getCustomerPhoneNumber());

    return Response
          .ok(getCustomer)
          .build();
  }

  /**
   * A Response method that deletes a customer.
   *
   * @param id defines a customer's id.
   * @return a Response that returns an empty object.
   */
  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteCustomerById(@PathParam("id") long id) {

    customerDaoRepository.deleteById(id);
    return Response
          .ok()
          .build();
  }
}
