package com.store.resource;

import com.store.core.OrderEntity;
import com.store.db.OrderDaoRepository;
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

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderEntityResource {

  private final OrderDaoRepository orderDaoRepository;

  public OrderEntityResource(OrderDaoRepository orderDaoRepository) {
    this.orderDaoRepository = orderDaoRepository;
  }

  /**
   * A Response method to find all orders.
   * @return a Response that returns a list of orders.
   */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<OrderEntity> findAll = orderDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  /**
   * A Response method the retrieves an order by id.
   * @param id defines an order's id.
   * @return a Response that returns the order entity object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    OrderEntity orderEntity = orderDaoRepository.getById(id).get();
    return Response
          .ok(orderEntity)
          .build();
  }

  /**
   * A Response method that saves an order.
   * @param orderEntity {@link OrderEntity} defines an order object.
   * @return a Response that saves an order and returns the OrderEntity object.
   */
  @POST
  @UnitOfWork
  @Path("add-order")
  public Response addOrder(OrderEntity orderEntity) {

    OrderEntity addOrder = orderDaoRepository.save(orderEntity);
    return Response
          .ok(addOrder)
          .build();
  }

  /**
   * A Response method to update an order.
   * @param id defines an order's id.
   * @return a Response that returns an updated order entity object.
   */
  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateOrder(@PathParam("id") long id, OrderEntity updateOrder) {

    OrderEntity getOrder = orderDaoRepository.getById(id).get();
    getOrder.setOrderTotal(updateOrder.getOrderTotal());

    return Response
          .ok(updateOrder)
          .build();
  }

  /**
   * A Response method that deletes an order by its id.
   * @param id defines an order's id.
   * @return a Response that returns nothing.
   */
  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteOrderById(@PathParam("id") long id) {

    orderDaoRepository.deleteById(id);
    return Response
          .ok()
          .build();
  }
}
