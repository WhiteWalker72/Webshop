package controller.rest;

import domain.component.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/cart")
public class CartResource {


    @Context
    HttpServletRequest request;

    @GET
    @Produces("application/json")
    public ArrayList<Product> getProducts() {

        HttpSession session = request.getSession();

        if (session.getAttribute("cart") != null ) {

            return (ArrayList<Product>)session.getAttribute("cart");
        }
        else {

            ArrayList<Product> cart = new ArrayList<>();

            //TODO: product should be package private, use ComponentServices instead
            cart.add(new Product("1","Mandje", 2.0, "1", 5));

            session.setAttribute("cart", cart);

            return cart;
        }
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

        HttpSession session = request.getSession();

        if ( session.getAttribute("cart") != null ) {

            ArrayList<Product> cart = (ArrayList<Product>)session.getAttribute("cart");

            cart.add(new Product("1","Mandje", 2.0, "1", 5));

            session.setAttribute("cart", cart);
        }
        else {

            session.setAttribute("cart", new ArrayList<Product>());
        }

        return Response.status(Response.Status.OK).build();
    }
}
