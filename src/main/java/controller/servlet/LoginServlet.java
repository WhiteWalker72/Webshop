package controller.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import domain.account.Account;
import exceptions.ObjectNotFoundException;
import domain.account.PassHashingStrategyImpl;
import persistence.PersistenceServices;

// username pass
@WebServlet(value = "/Login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");

        Account user = PersistenceServices.getInstance().getAccount(username);
        if (user == null) {
            request.getRequestDispatcher("/loginFailed.jsp").forward(request, response);
            return;
        }

        String salt = user.getSalt();
        String hashedPass = user.getPassword();
        if (new PassHashingStrategyImpl().passwordEquals(pass, salt, hashedPass)) {
            request.getRequestDispatcher("/loginSuccesful.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/loginFailed.jsp").forward(request, response);
        }
    }
}
