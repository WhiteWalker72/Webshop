package controller.rest;

import controller.request.ProductCategoryRequest;
import domain.component.Category;
import domain.component.ComponentServices;
import domain.component.Product;
import exceptions.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/prodcategory")
public class ProductCategoryResource {

    private final ComponentServices compServices = ComponentServices.getInstance();

    @Context
    HttpServletRequest request;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public List<Category> getCategoriesForProduct(@PathParam("id") int productId) {
        return compServices.getProductCategories(productId);
    }

    @POST
    @Consumes("application/json")
    public Response addProductToCategory(ProductCategoryRequest request) {
        if(request == null){
            return Response.status(400).entity("Please add request details.").build();
        }

        Category category = compServices.getCategory(request.getCategoryId());
        if (category == null) {
            return Response.status(400).entity("Category with id '" + request.getCategoryId()+ "' does not exists.").build();
        }
        Product product = compServices.getProduct(request.getProductId());
        if (product == null) {
            return Response.status(400).entity("Product with id '" + request.getProductId()+ "' does not exists.").build();
        }

        // Add product to category
        if (request.getAction().equalsIgnoreCase("add")) {
            List<Category> productCategories = compServices.getProductCategories(request.getProductId());
            if (productCategories.contains(category)) {
                return Response.status(400).entity("Product is already added to this category.").build();
            }

            try {
                compServices.addProductToCategory(product, category);
            } catch (ObjectNotFoundException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).build();
        }

        // Remove product from category
        if (request.getAction().equalsIgnoreCase("remove")) {
            try {
                compServices.removeProductFromCategory(product, category);
            } catch (ObjectNotFoundException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(400).entity("Invalid action, only add and remove is supported.").build();
    }

}
