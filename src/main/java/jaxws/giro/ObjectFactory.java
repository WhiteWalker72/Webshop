
package jaxws.giro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxws.giro package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetRandomNumber_QNAME = new QName("http://giro.jaxws/", "getRandomNumber");
    private final static QName _GetRandomNumberResponse_QNAME = new QName("http://giro.jaxws/", "getRandomNumberResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxws.giro
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRandomNumber }
     * 
     */
    public GetRandomNumber createGetRandomNumber() {
        return new GetRandomNumber();
    }

    /**
     * Create an instance of {@link GetRandomNumberResponse }
     * 
     */
    public GetRandomNumberResponse createGetRandomNumberResponse() {
        return new GetRandomNumberResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRandomNumber }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giro.jaxws/", name = "getRandomNumber")
    public JAXBElement<GetRandomNumber> createGetRandomNumber(GetRandomNumber value) {
        return new JAXBElement<GetRandomNumber>(_GetRandomNumber_QNAME, GetRandomNumber.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRandomNumberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giro.jaxws/", name = "getRandomNumberResponse")
    public JAXBElement<GetRandomNumberResponse> createGetRandomNumberResponse(GetRandomNumberResponse value) {
        return new JAXBElement<GetRandomNumberResponse>(_GetRandomNumberResponse_QNAME, GetRandomNumberResponse.class, null, value);
    }

}
