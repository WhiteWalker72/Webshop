package controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import domain.account.PassHashingStrategyImpl;

import domain.account.Account;
import exceptions.ObjectAlreadyExistsException;
import persistence.AccountDAOSQLImpl;
import persistence.PersistenceServices;
import utils.Pair;

@WebServlet(value="/Account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String street = req.getParameter("street");
        String number = req.getParameter("number");
        String postalCode = req.getParameter("postalcode");
        String city = req.getParameter("city");
        String username = req.getParameter("username");
        String psw = req.getParameter("psw");

        Pair<String, String> veiligWachtwoord = new PassHashingStrategyImpl().hashPassword(psw);

        try {
            PersistenceServices.getInstance().insertAccount(new Account(0, new Date(), 1, username, veiligWachtwoord.getLeft(), veiligWachtwoord.getRight()));
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/accountCreated.jsp").forward(req, resp);
    }
}


