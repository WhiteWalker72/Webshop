package controller;

import domain.category.Category;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(value="/header")
public class HeaderServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Houten manden"));
        categories.add(new Category("Stalen manden"));
        categories.add(new Category("Speciale manden"));

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/header.jsp").include(request, response);
    }

}
