
package com.brevy.spring.cxf.ws.services.fc.converter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for convert_CType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="convert_CType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://cxf.spring.brevy.com/services/FC/Converter/schema}param_SType"/>
 *         &lt;element name="radix" type="{http://cxf.spring.brevy.com/services/FC/Converter/schema}radix_CType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "convert_CType", propOrder = {
    "param",
    "radix"
})
public class ConvertCType {

    protected int param;
    protected int radix;

    /**
     * Gets the value of the param property.
     * 
     */
    public int getParam() {
        return param;
    }

    /**
     * Sets the value of the param property.
     * 
     */
    public void setParam(int value) {
        this.param = value;
    }

    /**
     * Gets the value of the radix property.
     * 
     */
    public int getRadix() {
        return radix;
    }

    /**
     * Sets the value of the radix property.
     * 
     */
    public void setRadix(int value) {
        this.radix = value;
    }

}
