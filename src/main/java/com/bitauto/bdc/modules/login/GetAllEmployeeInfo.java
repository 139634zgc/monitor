
package com.bitauto.bdc.modules.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isQueryDB" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "isQueryDB"
})
@XmlRootElement(name = "GetAllEmployeeInfo")
public class GetAllEmployeeInfo {

    protected boolean isQueryDB;

    /**
     * Gets the value of the isQueryDB property.
     * 
     */
    public boolean isIsQueryDB() {
        return isQueryDB;
    }

    /**
     * Sets the value of the isQueryDB property.
     * 
     */
    public void setIsQueryDB(boolean value) {
        this.isQueryDB = value;
    }

}
