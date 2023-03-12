package com.store.resource;

import com.store.core.DeliveryAddressEntity;
import com.store.db.DeliveryAddressDaoRepository;
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

@Path("/delivery-address")
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryAddressEntityResource {

  private final DeliveryAddressDaoRepository deliveryAddressDaoRepository;

   public DeliveryAddressEntityResource(DeliveryAddressDaoRepository deliveryAddressDaoRepository) {
      this.deliveryAddressDaoRepository = deliveryAddressDaoRepository;
   }

   @GET
   @UnitOfWork
   @Path("find-all")
   public Response findAll() {

      List<DeliveryAddressEntity> findAll = deliveryAddressDaoRepository.findAll();
      return Response
            .ok(findAll)
            .build();
   }

   @GET
   @UnitOfWork
   @Path("/{id}")
   public Response getById(@PathParam("id") long id) {

      DeliveryAddressEntity deliveryAddressEntity = deliveryAddressDaoRepository.getById(id).get();
      return Response
            .ok(deliveryAddressEntity)
            .build();
   }

   @POST
   @UnitOfWork
   @Path("add-delivery-address")
   public Response addDeliveryAddress(DeliveryAddressEntity deliveryAddressEntity) {

      DeliveryAddressEntity addDeliveryAddress = deliveryAddressDaoRepository.save(deliveryAddressEntity);
      return Response
            .ok(addDeliveryAddress)
            .build();
   }

   @PUT
   @UnitOfWork
   @Path("{id}")
   public Response updateDeliveryAddress(@PathParam("id") long id) {

      DeliveryAddressEntity updateDeliveryAddress = deliveryAddressDaoRepository.getById(id).get();
      return Response
            .ok(updateDeliveryAddress)
            .build();
   }

   @DELETE
   @UnitOfWork
   @Path("{id}")
   public Response deleteDeliveryAddressById(@PathParam("id") long id) {

      deliveryAddressDaoRepository.deleteById(id);
      return Response
            .ok()
            .build();
   }
}
