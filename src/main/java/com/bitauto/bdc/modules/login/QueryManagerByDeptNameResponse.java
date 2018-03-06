
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
 *         &lt;element name="QueryManagerByDeptNameResult" type="{http://tempuri.org/}ArrayOfEmployee" minOccurs="0"/>
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
    "queryManagerByDeptNameResult"
})
@XmlRootElement(name = "QueryManagerByDeptNameResponse")
public class QueryManagerByDeptNameResponse {

    @XmlElement(name = "QueryManagerByDeptNameResult")
    protected ArrayOfEmployee queryManagerByDeptNameResult;

    /**
     * Gets the value of the queryManagerByDeptNameResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public ArrayOfEmployee getQueryManagerByDeptNameResult() {
        return queryManagerByDeptNameResult;
    }

    /**
     * Sets the value of the queryManagerByDeptNameResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEmployee }
     *     
     */
    public void setQueryManagerByDeptNameResult(ArrayOfEmployee value) {
        this.queryManagerByDeptNameResult = value;
    }

}
