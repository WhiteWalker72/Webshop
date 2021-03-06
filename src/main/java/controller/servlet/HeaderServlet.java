package controller.servlet;

import domain.account.Account;
import domain.cart.Cart;
import domain.component.ComponentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/header")
public class HeaderServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("categories", ComponentServices.getInstance().getAllCategories());

        if(request.getSession().getAttribute("cart") == null) {

            request.getSession().setAttribute("cart", new Cart());
        }

        if(request.getSession().getAttribute("user") != null) {

            request.setAttribute("user", (request.getSession().getAttribute("user")));
        }

        request.setAttribute("cart", ((Cart)request.getSession().getAttribute("cart")).getProducts());

        request.getRequestDispatcher("/header.jsp").include(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        this.doGet(request, response);
    }

}
