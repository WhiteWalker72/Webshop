package controller.servlet;

import domain.component.ComponentServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(value = "/deals")
public class DealServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("products", ComponentServices.getInstance().getAllProducts()
                .stream().filter(product -> product.getActiveOffer() != null).collect(Collectors.toList()));

        request.getRequestDispatcher("/product/list.jsp").include(request, response);
    }

}
