package com.store.resource;

import com.store.core.ProductEntity;
import com.store.db.ProductDaoRepository;
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

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductEntityResource {

  private final ProductDaoRepository productDaoRepository;

  public ProductEntityResource(ProductDaoRepository productDaoRepository) {
    this.productDaoRepository = productDaoRepository;
  }

  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<ProductEntity> findAll = productDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    ProductEntity productEntity = productDaoRepository.getById(id).get();
    return Response
          .ok(productEntity)
          .build();
  }

  @POST
  @UnitOfWork
  @Path("add-product")
  public Response addProduct(ProductEntity productEntity) {

    ProductEntity addProduct = productDaoRepository.save(productEntity);
    return Response
          .ok(addProduct)
          .build();
  }

  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateProduct(@PathParam("id") long id) {

    ProductEntity updateProduct = productDaoRepository.getById(id).get();
    return Response
          .ok(updateProduct)
          .build();
  }

  @DELETE
  @UnitOfWork
  @Path("{id}")
  public Response deleteProductById(@PathParam("id") long id) {

    productDaoRepository.deleteById(id);
    return Response
          .ok()
          .build();
  }
}
