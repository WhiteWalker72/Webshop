package controller.servlet.checkout;

import domain.cart.Cart;
import domain.component.ComponentServices;
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
import java.util.ArrayList;
import java.util.Date;

@WebServlet(value="/checkout/success")
public class ProcessOrderServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if(request.getSession().getAttribute("cart") == null) {

            response.sendRedirect("/");
        } else {
            AddressDTO address = new AddressDTO(1, "Straat", "1", "1234AB", "Utrecht", "Nederland");

            int orderId = Integer.parseInt(PersistenceServices.getInstance().getNextOrderId());

            ArrayList<OrderLineDTO> orderLines = new ArrayList<>();
            orderLines.add(new OrderLineDTO(null, orderId, 1, 12.12, 1));
            OrderDTO order = new OrderDTO(orderId, 1, new Date(), address, orderLines);

            double price = 12.12;

            System.out.println(PaymentServices.getInstance().getGiroNumber("Klantnaam", address, price));

            System.out.println(orderId);
            try {
                PaymentServices.getInstance().saveOrder(order);

                request.getRequestDispatcher("/checkout/success.jsp").include(request, response);
            } catch (ObjectAlreadyExistsException e) {
                e.printStackTrace();
            }
        }

    }
}
