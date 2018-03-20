package controller.rest;

import domain.component.Category;
import domain.component.ComponentServices;
import domain.component.Product;
import dto.CategoryDTO;
import dto.ProductDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    private final ComponentServices compServices = ComponentServices.getInstance();

    @Context
    HttpServletRequest request;

    @GET
    @Produces("application/json")
    public List<Category> getAllCategories() {
        return compServices.getAllCategories();
    }

    @GET
    @Path("{id}/products")
    @Produces("application/json")
    public List<Product> getCategoriesForProduct(@PathParam("id") int productId) {
        return compServices.getAllProductsByCategory(productId);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCategory(@PathParam("id") int categoryId) {
        Category category = ComponentServices.getInstance().getCategory(categoryId);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            compServices.deleteCategory(category);
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateCategory(CategoryDTO categoryDTO) {
        if(categoryDTO == null){
            return Response.status(400).entity("Please add category details.").build();
        }

        try {
            compServices.updateCategory(categoryDTO);
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes("application/json")
    public Response addCategory(CategoryDTO categoryDTO) {
        if(categoryDTO == null || categoryDTO.getName() == null){
            return Response.status(400).entity("Please add category details.").build();
        }

        try {
            compServices.addNewCategory(categoryDTO);
        } catch (ObjectAlreadyExistsException e) {
            return Response.status(400).entity("Category with id '" + categoryDTO.getId() + "' already exists.").build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/{id}/products")
    @Consumes("application/json")
    public Response addProductToCategory(@PathParam("id") int id, ProductDTO productDTO) {
        Category category = compServices.getCategory(id);
        if (category == null) {
            return Response.status(400).entity("Category with id '" + id + "' does not exists.").build();
        }
        Product product = compServices.getProduct(productDTO.getId());
        if (product == null) {
            return Response.status(400).entity("Product with id '" + product.getId() + "' does not exists.").build();
        }

        List<Category> productCategories = compServices.getProductCategories(productDTO.getId());
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

    @DELETE
    @Path("/{id}/products/{productId}")
    @Consumes("application/json")
    public Response deleteProductFromCategory(@PathParam("id") int id, @PathParam("productId") int productId) {
        Category category = compServices.getCategory(id);
        if (category == null) {
            return Response.status(400).entity("Category with id '" + id + "' does not exists.").build();
        }
        Product product = compServices.getProduct(productId);
        if (product == null) {
            return Response.status(400).entity("Product with id '" + product.getId() + "' does not exists.").build();
        }

        List<Category> productCategories = compServices.getProductCategories(productId);
        if (!productCategories.contains(category)) {
            return Response.status(400).entity("Product is not in this category.").build();
        }

        try {
            compServices.removeProductFromCategory(product, category);
        } catch (ObjectNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
