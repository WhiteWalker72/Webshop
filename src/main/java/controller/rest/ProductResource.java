package controller.rest;

import domain.component.Category;
import domain.component.ComponentServices;
import domain.component.Product;
import dto.ProductDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
public class ProductResource {

    private final ComponentServices compServices = ComponentServices.getInstance();

    @Context
    HttpServletRequest request;

    @GET
    @Produces("application/json")
    public List<Product> getAllProducts() {
        return compServices.getAllProducts();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int productId) {
        Product product = ComponentServices.getInstance().getProduct(productId);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            compServices.deleteProduct(product);
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateProduct(ProductDTO productDTO) {
        if(productDTO == null){
            return Response.status(400).entity("Please add product details.").build();
        }

        try {
            compServices.updateProduct(productDTO);
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes("application/json")
    public Response addProduct(ProductDTO productDTO) {
        if(productDTO == null){
            return Response.status(400).entity("Please add product details.").build();
        }

        Category newCategory = null;
        for (Category category : compServices.getAllCategories()) {
            if (category.getName().equalsIgnoreCase("Nieuw")) {
                newCategory = category;
            }
        }
        if (newCategory == null) {
            return Response.status(400).entity("The 'Nieuw' category doesn't exist.").build();
        }

        try {
            compServices.addNewProduct(productDTO);
        } catch (ObjectAlreadyExistsException e) {
            return Response.status(400).entity("Product with id '" + productDTO.getId() + "' already exists.").build();
        }
        return Response.status(Response.Status.OK).build();
    }

}
