
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
 *         &lt;element name="GetEmployeeNumberByDomainAccountResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getEmployeeNumberByDomainAccountResult"
})
@XmlRootElement(name = "GetEmployeeNumberByDomainAccountResponse")
public class GetEmployeeNumberByDomainAccountResponse {

    @XmlElement(name = "GetEmployeeNumberByDomainAccountResult")
    protected String getEmployeeNumberByDomainAccountResult;

    /**
     * Gets the value of the getEmployeeNumberByDomainAccountResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetEmployeeNumberByDomainAccountResult() {
        return getEmployeeNumberByDomainAccountResult;
    }

    /**
     * Sets the value of the getEmployeeNumberByDomainAccountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetEmployeeNumberByDomainAccountResult(String value) {
        this.getEmployeeNumberByDomainAccountResult = value;
    }

}
