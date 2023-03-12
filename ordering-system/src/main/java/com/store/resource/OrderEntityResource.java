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

  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<OrderEntity> findAll = orderDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    OrderEntity orderEntity = orderDaoRepository.getById(id).get();
    return Response
          .ok(orderEntity)
          .build();
  }

  @POST
  @UnitOfWork
  @Path("add-order")
  public Response addOrder(OrderEntity orderEntity) {

    OrderEntity addOrder = orderDaoRepository.save(orderEntity);
    return Response
          .ok(addOrder)
          .build();
  }

  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateOrder(@PathParam("id") long id) {

    OrderEntity updateOrder = orderDaoRepository.getById(id).get();
    return Response
          .ok(updateOrder)
          .build();
  }

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
