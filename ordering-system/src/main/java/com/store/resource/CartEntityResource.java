package com.store.resource;

import com.store.core.CartEntity;
import com.store.db.CartDaoRepository;
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

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
public class CartEntityResource {

  private final CartDaoRepository cartDaoRepository;

  public CartEntityResource(CartDaoRepository cartDaoRepository) {
    this.cartDaoRepository = cartDaoRepository;
  }

  /**
   * A Response that fetches all carts.
   *
   * @return a Response that returns a list of carts.
   */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<CartEntity> findAll = cartDaoRepository.findAll();
    return Response
        .ok(findAll)
        .build();
  }

  /**
   * A Response that retrieves a cart by id.
   *
   * @param id defines a cart's id.
   * @return a Response that returns a cart object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    CartEntity cartEntity = cartDaoRepository.getById(id).orElseThrow(
        () -> {
        return new DataNotFoundException("Cart with id " + id + " does not exist");
      });
    return Response
        .ok(cartEntity)
        .build();
  }

  /**
   * A Response method that saves a cart.
   *
   * @param cartEntity {@link CartEntity} defines the CartEntity class.
   * @return a Response that returns a cart object.
   */
  @POST
  @UnitOfWork
  @Path("add-cart")
  public Response addcart(CartEntity cartEntity) {

    CartEntity addcart = cartDaoRepository.save(cartEntity);
    return Response
        .ok(addcart)
        .build();
  }

  /**
   * A Response method that deletes a cart.
   *
   * @param id defines a cart's id.
   * @return a Response that returns an empty object.
   */
  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteCartById(@PathParam("id") long id) {

    cartDaoRepository.deleteById(id);
    return Response
        .ok()
        .build();
  }
}
