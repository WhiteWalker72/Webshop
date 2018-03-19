package controller.servlet;

import domain.component.ComponentServices;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

@WebServlet(value="/Category/*")
public class CategoryServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("products", ComponentServices.getInstance().getAllProductsByCategory(parseInt(ServletUtils.getPathId(request.getPathInfo()))));
        request.getRequestDispatcher("/category.jsp").include(request, response);
    }

}
