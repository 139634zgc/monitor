
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
 *         &lt;element name="GetEmployeeByDomainAccountResult" type="{http://tempuri.org/}Employee" minOccurs="0"/>
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
    "getEmployeeByDomainAccountResult"
})
@XmlRootElement(name = "GetEmployeeByDomainAccountResponse")
public class GetEmployeeByDomainAccountResponse {

    @XmlElement(name = "GetEmployeeByDomainAccountResult")
    protected Employee getEmployeeByDomainAccountResult;

    /**
     * Gets the value of the getEmployeeByDomainAccountResult property.
     * 
     * @return
     *     possible object is
     *     {@link Employee }
     *     
     */
    public Employee getGetEmployeeByDomainAccountResult() {
        return getEmployeeByDomainAccountResult;
    }

    /**
     * Sets the value of the getEmployeeByDomainAccountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Employee }
     *     
     */
    public void setGetEmployeeByDomainAccountResult(Employee value) {
        this.getEmployeeByDomainAccountResult = value;
    }

}
