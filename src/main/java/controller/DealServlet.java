package controller;

import domain.component.ComponentServices;
import dto.ProductDTO;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/deals")
public class DealServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ArrayList<ProductDTO> products = new ArrayList<>();

        products.add(ComponentServices.getInstance().getProductDTO(1));

        request.setAttribute("products", products);

        request.getRequestDispatcher("/product/list.jsp").include(request, response);
    }
}
