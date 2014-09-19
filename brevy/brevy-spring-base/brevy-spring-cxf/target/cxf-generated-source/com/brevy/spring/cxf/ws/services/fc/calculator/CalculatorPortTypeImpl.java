
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.brevy.spring.cxf.ws.services.fc.calculator;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-06-14T09:42:50.949+08:00
 * Generated source version: 2.7.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "CalculatorService",
                      portName = "CalculatorPort",
                      targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator",
                      endpointInterface = "com.brevy.spring.cxf.ws.services.fc.calculator.CalculatorPortType")
                      
public class CalculatorPortTypeImpl implements CalculatorPortType {

    private static final Logger LOG = Logger.getLogger(CalculatorPortTypeImpl.class.getName());

    /* (non-Javadoc)
     * @see com.brevy.spring.cxf.ws.services.fc.calculator.CalculatorPortType#divide(java.math.BigDecimal  param1 ,)java.math.BigDecimal  param2 ,)com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType  settings )*
     */
    public java.math.BigDecimal divide(java.math.BigDecimal param1,java.math.BigDecimal param2,com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings) { 
        LOG.info("Executing operation divide");
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(settings);
        try {
            java.math.BigDecimal _return = new java.math.BigDecimal("0");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.brevy.spring.cxf.ws.services.fc.calculator.CalculatorPortType#add(java.math.BigDecimal  param1 ,)java.math.BigDecimal  param2 ,)com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType  settings )*
     */
    public java.math.BigDecimal add(java.math.BigDecimal param1,java.math.BigDecimal param2,com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings) { 
        LOG.info("Executing operation add");
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(settings);
        try {
            java.math.BigDecimal _return = new java.math.BigDecimal("0");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.brevy.spring.cxf.ws.services.fc.calculator.CalculatorPortType#minus(java.math.BigDecimal  param1 ,)java.math.BigDecimal  param2 ,)com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType  settings )*
     */
    public java.math.BigDecimal minus(java.math.BigDecimal param1,java.math.BigDecimal param2,com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings) { 
        LOG.info("Executing operation minus");
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(settings);
        try {
            java.math.BigDecimal _return = new java.math.BigDecimal("0");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.brevy.spring.cxf.ws.services.fc.calculator.CalculatorPortType#multiply(java.math.BigDecimal  param1 ,)java.math.BigDecimal  param2 ,)com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType  settings )*
     */
    public java.math.BigDecimal multiply(java.math.BigDecimal param1,java.math.BigDecimal param2,com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings) { 
        LOG.info("Executing operation multiply");
        System.out.println(param1);
        System.out.println(param2);
        System.out.println(settings);
        try {
            java.math.BigDecimal _return = new java.math.BigDecimal("0");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
