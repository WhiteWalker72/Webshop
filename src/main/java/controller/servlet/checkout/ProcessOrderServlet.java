package controller.servlet.checkout;

import domain.account.Account;
import domain.cart.Cart;
import domain.component.ComponentServices;
import domain.component.Product;
import domain.payment.PaymentServices;
import dto.AddressDTO;
import dto.OrderDTO;
import dto.OrderLineDTO;
import exceptions.ObjectAlreadyExistsException;
import persistence.PersistenceServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@WebServlet(value="/checkout/success")
public class ProcessOrderServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(request.getSession().getAttribute("cart") == null || request.getSession().getAttribute("user") == null) {

            response.sendRedirect("/");
        } else {
            AddressDTO address = new AddressDTO(1, "Straat", "1", "1234AB", "Utrecht", "Nederland");

            Account user = (Account) request.getSession().getAttribute("user");
            Cart cart = (Cart) request.getSession().getAttribute("cart");

            int orderId = Integer.parseInt(PersistenceServices.getInstance().getNextOrderId());

            ArrayList<OrderLineDTO> orderLines = new ArrayList<>();

            double totalprice = 0;

            for(Map.Entry<Product, Integer> item : cart.getProducts().entrySet()) {
                Product product = item.getKey();
                int amount = item.getValue();

                double price = product.getPrice();

                if(product.getActiveOffer() != null) {

                    price = product.getActiveOffer().getOfferPrice();
                }

                totalprice += price*amount;

                orderLines.add(new OrderLineDTO(null, orderId, amount, price, product.getId()));
            }

            int giro = PaymentServices.getInstance().getGiroNumber(user.getUsername(), address, totalprice).intValue();

            OrderDTO order = new OrderDTO(orderId, user.getCustomerId(), new Date(), address, orderLines, giro);

            try {
                PaymentServices.getInstance().saveOrder(order);

                request.getSession().removeAttribute("cart");
                request.getRequestDispatcher("/checkout/success.jsp").include(request, response);
            } catch (ObjectAlreadyExistsException e) {
                e.printStackTrace();
            }
        }

    }
}
