package com.store.resource;

import com.store.core.ShippingAddressEntity;
import com.store.db.ShippingAddressDaoRepository;
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

@Path("/shipping-address")
@Produces(MediaType.APPLICATION_JSON)
public class ShippingAddressEntityResource {

  private final ShippingAddressDaoRepository shippingAddressDaoRepository;

  public ShippingAddressEntityResource(ShippingAddressDaoRepository shippingAddressDaoRepository) {
    this.shippingAddressDaoRepository = shippingAddressDaoRepository;
  }

  /**
  * A Response method to fetch all shipping addresses.
  * @return a Response that returns a list of shipping addresses.
  */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<ShippingAddressEntity> findAll = shippingAddressDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  /**
   * A Response method that retrieves a shipping address by its id.
   * @param id defines a shipping address's id.
   * @return a Response that returns a shipping address object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    ShippingAddressEntity shippingAddressEntity = shippingAddressDaoRepository.getById(id).get();
    return Response
          .ok(shippingAddressEntity)
          .build();
  }

  /**
   * A Response method that saves a shipping address.
   * @param shippingAddressEntity {@link ShippingAddressEntity}
   *      defines the ShippingAddressEntity class.
   * @return a Response that returns a shipping address object.
   */
  @POST
  @UnitOfWork
  @Path("add-shipping-address")
  public Response addShippingAddress(ShippingAddressEntity shippingAddressEntity) {

    ShippingAddressEntity addShippingAddress =
        shippingAddressDaoRepository.save(shippingAddressEntity);
    return Response
          .ok(addShippingAddress)
          .build();
  }

  /**
   * A Response method that updates a shipping address.
   * @param id defines a shipping address's id.
   * @return a Response that returns a shipping address object.
   */
  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateShippingAddress(@PathParam("id") long id) {

    ShippingAddressEntity updateShippingAddress = shippingAddressDaoRepository.getById(id).get();
    return Response
          .ok(updateShippingAddress)
          .build();
  }

  /**
   * A Response method that deletes a shipping address.
   * @param id defines a shipping address's id.
   * @return a Response that returns nothing.
   */
  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteShippingAddressById(@PathParam("id") long id) {

    shippingAddressDaoRepository.deleteById(id);
    return Response
          .ok()
          .build();
  }
}
