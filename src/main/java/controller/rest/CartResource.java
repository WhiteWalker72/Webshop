package controller.rest;

import domain.product.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Path("/cart")
public class CartResource {

    @GET
    @Produces("application/json")
    public ArrayList<Product> getProducts() {

        ArrayList<Product> cart = new ArrayList<>();

        cart.add(new Product("1","Mandje", "test", 1));

        return cart;
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int customerId) {

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateProduct(@PathParam("id") int customerId, String customer) {

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes("application/json")
    public Response addProduct(String customer) {

        return Response.status(Response.Status.OK).build();
    }
}
