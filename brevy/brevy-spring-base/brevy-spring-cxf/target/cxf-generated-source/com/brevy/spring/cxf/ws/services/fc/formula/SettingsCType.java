
package com.brevy.spring.cxf.ws.services.fc.formula;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for settings_CType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="settings_CType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scale" type="{http://cxf.spring.brevy.com/services/FC/Formula/schema}scale_SType"/>
 *         &lt;element name="roundingMode" type="{http://cxf.spring.brevy.com/services/FC/Formula/schema}roundingMode_SType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "settings_CType", propOrder = {
    "scale",
    "roundingMode"
})
public class SettingsCType {

    protected int scale;
    protected int roundingMode;

    /**
     * Gets the value of the scale property.
     * 
     */
    public int getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     */
    public void setScale(int value) {
        this.scale = value;
    }

    /**
     * Gets the value of the roundingMode property.
     * 
     */
    public int getRoundingMode() {
        return roundingMode;
    }

    /**
     * Sets the value of the roundingMode property.
     * 
     */
    public void setRoundingMode(int value) {
        this.roundingMode = value;
    }

}
