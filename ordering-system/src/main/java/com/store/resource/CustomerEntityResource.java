package com.store.resource;

import com.store.core.CustomerEntity;
import com.store.db.CustomerDaoRepository;
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

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerEntityResource {

   private final CustomerDaoRepository customerDaoRepository;

   public CustomerEntityResource(CustomerDaoRepository customerDaoRepository) {
      this.customerDaoRepository = customerDaoRepository;
   }

   @GET
   @UnitOfWork
   @Path("find-all")
   public Response findAll() {

      List<CustomerEntity> findAll = customerDaoRepository.findAll();
      return Response
            .ok(findAll)
            .build();
   }

   @GET
   @UnitOfWork
   @Path("/{id}")
   public Response getById(@PathParam("id") long id) {

      CustomerEntity customerEntity = customerDaoRepository.getById(id).get();
      return Response
            .ok(customerEntity)
            .build();
   }

   @POST
   @UnitOfWork
   @Path("add-customer")
   public Response addCustomer(CustomerEntity customerEntity) {

      CustomerEntity addCustomer = customerDaoRepository.save(customerEntity);
      return Response
            .ok(addCustomer)
            .build();
   }

   @PUT
   @UnitOfWork
   @Path("{id}")
   public Response updateCustomer(@PathParam("id") long id) {

      CustomerEntity updateCustomer = customerDaoRepository.getById(id).get();
      return Response
            .ok(updateCustomer)
            .build();
   }

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
