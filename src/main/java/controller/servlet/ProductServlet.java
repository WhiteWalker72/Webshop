package controller.servlet;

import domain.component.ComponentServices;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Product/*")
public class ProductServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = ServletUtils.getPathId(request.getPathInfo());

        request.setAttribute("product", ComponentServices.getInstance().getProduct(Integer.parseInt(id)));
        request.getRequestDispatcher("/product/product.jsp").include(request, response);
    }

}
