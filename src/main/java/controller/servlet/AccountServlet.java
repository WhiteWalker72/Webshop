package controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import domain.account.Address;
import domain.account.Customer;
import domain.account.PassHashingStrategyImpl;

import domain.account.Account;
import dto.AddressDTO;
import exceptions.ObjectAlreadyExistsException;
import persistence.PersistenceServices;
import utils.Pair;

@WebServlet(value = "/Account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String street = req.getParameter("street");
        String number = req.getParameter("number");
        String postalCode = req.getParameter("postalcode");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String username = req.getParameter("username");
        String psw = req.getParameter("psw");

        if (PersistenceServices.getInstance().getAccount(username) != null) {
            req.getRequestDispatcher("/createaccount.jsp").forward(req, resp);
            return;
        }

        Pair<String, String> saltPassPair = new PassHashingStrategyImpl().hashPassword(psw);
        try {
            int adresId = Integer.parseInt(PersistenceServices.getInstance().getNextAddressId());
            System.out.println(adresId);
            PersistenceServices.getInstance().insertAddress(new AddressDTO(adresId, street, number, postalCode, city, country));

            int customerId = Integer.parseInt(PersistenceServices.getInstance().getNextCustomerId());
            System.out.println(customerId);
            PersistenceServices.getInstance().insertCustomer(new Customer(customerId
                    , name, adresId));

            PersistenceServices.getInstance().insertAccount(new Account(Integer.parseInt(PersistenceServices.getInstance().getNextAccountId())
                    , new Date(), 1, username, saltPassPair.getLeft(), customerId, saltPassPair.getRight()));

        } catch (ObjectAlreadyExistsException e) {
            // req.getRequestDispatcher("/createaccount.jsp").forward(req, resp);
            resp.sendRedirect("/createaccount.jsp");
            return;
        }

        req.getRequestDispatcher("/accountCreated.jsp").forward(req, resp);
    }
}


