package controller.rest;

import domain.component.Category;
import domain.component.ComponentServices;
import dto.CategoryDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/category")
public class CategoryResource {

    private final ComponentServices compServices = ComponentServices.getInstance();

    @Context
    HttpServletRequest request;

    @GET
    @Produces("application/json")
    public List<Category> getAllCategories() {
        return compServices.getAllCategories();
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



}
