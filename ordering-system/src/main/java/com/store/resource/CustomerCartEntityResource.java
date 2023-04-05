package com.store.resource;

import com.store.core.CustomerCartEntity;
import com.store.db.CustomerCartDaoRepository;
import com.store.exception.DataNotFoundException;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * A resource that includes CRUD or other endpoints for customer carts.
 */
@Path("/customer-carts")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerCartEntityResource {

  private final CustomerCartDaoRepository customerCartDaoRepository;

  public CustomerCartEntityResource(CustomerCartDaoRepository customerCartDaoRepository) {
    this.customerCartDaoRepository = customerCartDaoRepository;
  }

  /**
   * A Response that fetches all customers' carts.
   *
   * @return a Response that returns a list of customers' carts.
   */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<CustomerCartEntity> findAll = customerCartDaoRepository.findAll();
    return Response
        .ok(findAll)
        .build();
  }

  /**
   * A Response that retrieves a customer's cart by id.
   *
   * @param id defines a customer's cart id.
   * @return a Response that returns a customer's cart object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    CustomerCartEntity customerCartEntity = customerCartDaoRepository.getById(id).orElseThrow(
        () -> {
        return new DataNotFoundException("Customer's cart with id " + id + " does not exist");
      });
    return Response
        .ok(customerCartEntity)
        .build();
  }

  /**
   * A Response method that saves a customer's cart.
   *
   * @param customerCartEntity customerCartEntity {@link CustomerCartEntity}
   *      defines the customer's CartEntity class.
   * @return a Response that returns a customer's cart object.
   */
  @POST
  @UnitOfWork
  @Path("add-customer-cart")
  public Response addcart(CustomerCartEntity customerCartEntity) {

    CustomerCartEntity addCustomerCart = customerCartDaoRepository.save(customerCartEntity);
    return Response
        .ok(addCustomerCart)
        .build();
  }

  /**
   * A Response method that deletes a customer's cart.
   *
   * @param id defines a customer's cart id.
   * @return a Response that returns an empty object.
   */
  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteCustomerCartById(@PathParam("id") long id) {

    customerCartDaoRepository.deleteById(id);
    return Response
        .ok()
        .build();
  }
}
