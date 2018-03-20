package controller.servlet.checkout;

import domain.cart.Cart;
import domain.component.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value="/checkout/orderoverview")
public class OrderOverviewServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(request.getSession().getAttribute("cart") == null || request.getSession().getAttribute("user") == null) {

            response.sendRedirect("/");
        } else {

            double totalprice = 0;

            Cart cart = (Cart)request.getSession().getAttribute("cart");

            for(Map.Entry<Product, Integer> item : cart.getProducts().entrySet()) {
                Product product = item.getKey();
                int amount = item.getValue();

                double price = product.getPrice();

                if (product.getActiveOffer() != null) {

                    price = product.getActiveOffer().getOfferPrice();
                }

                totalprice += price*amount;
            }

            request.setAttribute("cart", cart.getProducts());
            request.getRequestDispatcher("/checkout/orderoverview.jsp").include(request, response);
        }

    }

}
