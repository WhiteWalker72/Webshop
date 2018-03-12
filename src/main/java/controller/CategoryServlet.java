package controller;

import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value="/Category/*")
public class CategoryServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        try {
            request.getRequestDispatcher("/index.jsp").include(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.println("<h1>Category with id: " + ServletUtils.getPathId(request.getPathInfo()) + "</h1>");
        out.close();
    }

}
