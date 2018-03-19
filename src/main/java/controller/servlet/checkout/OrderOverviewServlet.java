package controller.servlet.checkout;

import domain.cart.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/checkout/orderoverview")
public class OrderOverviewServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(request.getSession().getAttribute("cart") == null) {

            response.sendRedirect("/");
        } else {

            request.setAttribute("cart", ((Cart)request.getSession().getAttribute("cart")).getProducts());
            request.getRequestDispatcher("/checkout/orderoverview.jsp").include(request, response);
        }

    }

}
