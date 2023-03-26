package com.store.resource;

import com.store.core.OrderDetailEntity;
import com.store.db.OrderDetailDaoRepository;
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

@Path("/order-details")
@Produces(MediaType.APPLICATION_JSON)
public class OrderDetailEntityResource {

  private final OrderDetailDaoRepository orderDetailDaoRepository;

  public OrderDetailEntityResource(OrderDetailDaoRepository orderDetailDaoRepository) {
    this.orderDetailDaoRepository = orderDetailDaoRepository;
  }

  /**
   * A Response method to find all order details.
   * @return a Response that returns a list of order details.
   */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<OrderDetailEntity> findAll = orderDetailDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  /**
   * A Response method the retrieves an order detail by id.
   * @param id defines an order's id.
   * @return a Response that returns an order detail object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    OrderDetailEntity orderDetailEntity = orderDetailDaoRepository.getById(id).get();
    return Response
          .ok(orderDetailEntity)
          .build();
  }

  /**
   * A Response method that saves an order detail.
   * @param orderDetailEntity {@link OrderDetailEntity} defines an order detail object.
   * @return a Response that saves an order and returns the OrderDetailEntity object.
   */
  @POST
  @UnitOfWork
  @Path("add-order-detail")
  public Response addOrderDetail(OrderDetailEntity orderDetailEntity) {

    OrderDetailEntity addOrderDetail = orderDetailDaoRepository.save(orderDetailEntity);
    return Response
          .ok(addOrderDetail)
          .build();
  }

  /**
   * A Response method to update an order detail.
   * @param id defines an order detail's id.
   * @return a Response that returns an updated OrderDetail Entity object.
   */
  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateOrderDetail(@PathParam("id") long id, OrderDetailEntity updateOrderDetail) {

    OrderDetailEntity getOrderDetail = orderDetailDaoRepository.getById(id).get();
    getOrderDetail.setPaymentType(updateOrderDetail.getPaymentType());
    getOrderDetail.setOrderDetailTotal(updateOrderDetail.getOrderDetailTotal());

    return Response
          .ok(updateOrderDetail)
          .build();
  }

  /**
   * A Response method that deletes an order detail by its id.
   * @param id defines an order detail's id.
   * @return a Response that returns nothing.
   */
  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteOrderDetailById(@PathParam("id") long id) {

    orderDetailDaoRepository.deleteById(id);
    return Response
          .ok()
          .build();
  }
}
