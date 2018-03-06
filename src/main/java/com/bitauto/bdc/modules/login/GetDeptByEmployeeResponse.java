
package com.bitauto.bdc.modules.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetDeptByEmployeeResult" type="{http://tempuri.org/}Department" minOccurs="0"/>
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
    "getDeptByEmployeeResult"
})
@XmlRootElement(name = "GetDeptByEmployeeResponse")
public class GetDeptByEmployeeResponse {

    @XmlElement(name = "GetDeptByEmployeeResult")
    protected Department getDeptByEmployeeResult;

    /**
     * Gets the value of the getDeptByEmployeeResult property.
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getGetDeptByEmployeeResult() {
        return getDeptByEmployeeResult;
    }

    /**
     * Sets the value of the getDeptByEmployeeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setGetDeptByEmployeeResult(Department value) {
        this.getDeptByEmployeeResult = value;
    }

}
