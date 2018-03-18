package controller.rest;

import controller.request.AddProductToCartRequest;
import domain.cart.Cart;
import domain.component.ComponentServices;
import domain.component.Product;

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
    public Map<Product, Integer> getProducts() {
        HttpSession session = request.getSession();

        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }

        Cart cart = (Cart) session.getAttribute("cart");
        return cart.getProducts();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id") int productId) {

        this.getCart().remove(productId);

        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateProduct(AddProductToCartRequest product) {

        this.getCart().edit(product.getId(), product.getAmount());

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Product addProduct(AddProductToCartRequest product) {

        this.getCart().add(product.getId(), product.getAmount());

        return ComponentServices.getInstance().getProduct(product.getId());
    }

    private Cart getCart() {

        HttpSession session = request.getSession();

        Cart cart;

        if (session.getAttribute("cart") != null)
            cart = (Cart) session.getAttribute("cart");
         else
            cart = new Cart();

        session.setAttribute("cart", cart);

        return cart;
    }

}
