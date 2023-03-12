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

  @GET
   @UnitOfWork
   @Path("find-all")
   public Response findAll() {

      List<OrderDetailEntity> findAll = orderDetailDaoRepository.findAll();
      return Response
            .ok(findAll)
            .build();
   }

   @GET
   @UnitOfWork
   @Path("/{id}")
   public Response getById(@PathParam("id") long id) {

      OrderDetailEntity orderDetailEntity = orderDetailDaoRepository.getById(id).get();
      return Response
            .ok(orderDetailEntity)
            .build();
   }

   @POST
   @UnitOfWork
   @Path("add-order-detail")
   public Response addOrderDetail(OrderDetailEntity orderDetailEntity) {

      OrderDetailEntity addOrderDetail = orderDetailDaoRepository.save(orderDetailEntity);
      return Response
            .ok(addOrderDetail)
            .build();
   }

   @PUT
   @UnitOfWork
   @Path("{id}")
   public Response updateOrderDetail(@PathParam("id") long id) {

      OrderDetailEntity updateOrderDetail = orderDetailDaoRepository.getById(id).get();
      return Response
            .ok(updateOrderDetail)
            .build();
   }

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
