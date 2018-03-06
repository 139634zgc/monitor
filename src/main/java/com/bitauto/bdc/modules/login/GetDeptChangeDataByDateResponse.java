
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
 *         &lt;element name="GetDeptChangeDataByDateResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getDeptChangeDataByDateResult"
})
@XmlRootElement(name = "GetDeptChangeDataByDateResponse")
public class GetDeptChangeDataByDateResponse {

    @XmlElement(name = "GetDeptChangeDataByDateResult")
    protected String getDeptChangeDataByDateResult;

    /**
     * Gets the value of the getDeptChangeDataByDateResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetDeptChangeDataByDateResult() {
        return getDeptChangeDataByDateResult;
    }

    /**
     * Sets the value of the getDeptChangeDataByDateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetDeptChangeDataByDateResult(String value) {
        this.getDeptChangeDataByDateResult = value;
    }

}
