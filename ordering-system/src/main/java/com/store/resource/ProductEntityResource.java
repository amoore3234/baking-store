package com.store.resource;

import com.store.core.ProductEntity;
import com.store.db.ProductDaoRepository;
import com.store.exception.DataNotFoundException;
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

  /**
   * A Response method that fetches all products.
   * @return a Response that returns a list of products.
   */
  @GET
  @UnitOfWork
  @Path("find-all")
  public Response findAll() {

    List<ProductEntity> findAll = productDaoRepository.findAll();
    return Response
          .ok(findAll)
          .build();
  }

  /**
   * A Response method that retrieves a product by id.
   * @param id defines a product's id.
   * @return a Response that returns a product object.
   */
  @GET
  @UnitOfWork
  @Path("/{id}")
  public Response getById(@PathParam("id") long id) {

    ProductEntity productEntity = productDaoRepository.getById(id).orElseThrow(
        () -> {
        return new DataNotFoundException("Product with id " + id + " does not exist");
      });
    return Response
          .ok(productEntity)
          .build();
  }

  /**
   * A Response method that saves a product.
   * @param productEntity {@link ProductEntity}
   *      defines the ProductEntity class.
   * @return a Response that returns a product object.
   */
  @POST
  @UnitOfWork
  @Path("add-product")
  public Response addProduct(ProductEntity productEntity) {

    ProductEntity addProduct = productDaoRepository.save(productEntity);
    return Response
          .ok(addProduct)
          .build();
  }

  /**
   * A Response method that updates a product.
   * @param id defines a product's id.
   * @return a Response that returns a product object.
   */
  @PUT
  @UnitOfWork
  @Path("{id}")
  public Response updateProduct(@PathParam("id") long id, ProductEntity updateProduct) {

    ProductEntity getProduct = productDaoRepository.getById(id).orElseThrow(
        () -> {
        return new DataNotFoundException("Product with id " + id + " does not exist");
      });
    getProduct.setProductName(updateProduct.getProductName());
    getProduct.setProductType(updateProduct.getProductType());
    getProduct.setProductPrice(updateProduct.getProductPrice());
    getProduct.setProductDescription(updateProduct.getProductDescription());
    getProduct.setProductQuantity(updateProduct.getProductQuantity());

    return Response
          .ok(updateProduct)
          .build();
  }

  /**
   * A Response method that deletes a product.
   * @param id defines a product's id.
   * @return a Response that returns an empty object.
   */
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
