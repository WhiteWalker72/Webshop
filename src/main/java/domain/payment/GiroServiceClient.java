package domain.payment;

import dto.AddressDTO;
import jaxws.giro.GiroServiceImpl;
import jaxws.giro.GiroServiceImplService;

import java.math.BigInteger;

public class GiroServiceClient {

    BigInteger getRandomNumber(String customerName, AddressDTO dto, double cost) {
        GiroServiceImpl webservice = new GiroServiceImplService().getGiroServiceImplPort();
        return webservice.getRandomNumber(customerName, dto.getStreet(), dto.getNumber(), dto.getPostalCode(), dto.getCity(), dto.getCountry(), cost);
    }

}
