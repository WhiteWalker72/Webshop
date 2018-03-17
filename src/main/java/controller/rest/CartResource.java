package controller.rest;

import controller.request.AddProductToCartRequest;
import domain.cart.Cart;
import dto.ProductDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/cart")
public class CartResource {


    @Context
    HttpServletRequest request;

    @GET
    @Produces("application/json")
    public Map<ProductDTO, Integer> getProducts() {

        HttpSession session = request.getSession();

        if (session.getAttribute("cart") == null) {

            session.setAttribute("cart", new Cart());
        }

        return (Map<ProductDTO, Integer>) session.getAttribute("cart");
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int customerId) {

        if (request.getSession().getAttribute("cart") != null) {

            request.getSession().setAttribute("cart", new Cart());
        }

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
    public Response addProduct(AddProductToCartRequest product) {

        HttpSession session = request.getSession();

        if (session.getAttribute("cart") != null) {

            Cart cart = (Cart) session.getAttribute("cart");

            cart.add(product.getId(), product.getAmount());

            session.setAttribute("cart", cart);
        } else {

            session.setAttribute("cart", new Cart());
        }

        return Response.status(Response.Status.OK).build();
    }

}
