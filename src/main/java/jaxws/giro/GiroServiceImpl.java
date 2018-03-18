
package jaxws.giro;

import java.math.BigInteger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebService(name = "GiroServiceImpl", targetNamespace = "http://giro.jaxws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GiroServiceImpl {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     * @return
     *     returns java.math.BigInteger
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getRandomNumber", targetNamespace = "http://giro.jaxws/", className = "jaxws.giro.GetRandomNumber")
    @ResponseWrapper(localName = "getRandomNumberResponse", targetNamespace = "http://giro.jaxws/", className = "jaxws.giro.GetRandomNumberResponse")
    @Action(input = "http://giro.jaxws/GiroServiceImpl/getRandomNumberRequest", output = "http://giro.jaxws/GiroServiceImpl/getRandomNumberResponse")
    public BigInteger getRandomNumber(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        double arg6);

}