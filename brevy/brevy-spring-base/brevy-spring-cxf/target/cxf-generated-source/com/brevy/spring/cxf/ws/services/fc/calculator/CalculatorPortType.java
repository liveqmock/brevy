package com.brevy.spring.cxf.ws.services.fc.calculator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-06-14T09:42:51.011+08:00
 * Generated source version: 2.7.5
 * 
 */
@WebService(targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator", name = "CalculatorPortType")
@XmlSeeAlso({ObjectFactory.class})
public interface CalculatorPortType {

    @WebResult(name = "result", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
    @RequestWrapper(localName = "divide", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.DivideRequestCType")
    @WebMethod(action = "http://cxf.spring.brevy.com/services/FC/Calculator/divide")
    @ResponseWrapper(localName = "divideResponse", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.DivideResponseCType")
    public java.math.BigDecimal divide(
        @WebParam(name = "param1", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param1,
        @WebParam(name = "param2", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param2,
        @WebParam(name = "settings", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings
    );

    @WebResult(name = "result", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
    @RequestWrapper(localName = "add", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.AddRequestCType")
    @WebMethod(action = "http://cxf.spring.brevy.com/services/FC/Calculator/add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.AddResponseCType")
    public java.math.BigDecimal add(
        @WebParam(name = "param1", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param1,
        @WebParam(name = "param2", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param2,
        @WebParam(name = "settings", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings
    );

    @WebResult(name = "result", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
    @RequestWrapper(localName = "minus", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.MinusRequestCType")
    @WebMethod(action = "http://cxf.spring.brevy.com/services/FC/Calculator/minus")
    @ResponseWrapper(localName = "minusResponse", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.MinusResponseCType")
    public java.math.BigDecimal minus(
        @WebParam(name = "param1", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param1,
        @WebParam(name = "param2", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param2,
        @WebParam(name = "settings", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings
    );

    @WebResult(name = "result", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
    @RequestWrapper(localName = "multiply", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.MultiplyRequestCType")
    @WebMethod(action = "http://cxf.spring.brevy.com/services/FC/Calculator/multiply")
    @ResponseWrapper(localName = "multiplyResponse", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema", className = "com.brevy.spring.cxf.ws.services.fc.calculator.MultiplyResponseCType")
    public java.math.BigDecimal multiply(
        @WebParam(name = "param1", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param1,
        @WebParam(name = "param2", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        java.math.BigDecimal param2,
        @WebParam(name = "settings", targetNamespace = "http://cxf.spring.brevy.com/services/FC/Calculator/schema")
        com.brevy.spring.cxf.ws.services.fc.calculator.SettingsCType settings
    );
}
