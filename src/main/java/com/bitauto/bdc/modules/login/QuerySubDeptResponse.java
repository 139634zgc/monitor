
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
 *         &lt;element name="QuerySubDeptResult" type="{http://tempuri.org/}ArrayOfDepartment" minOccurs="0"/>
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
    "querySubDeptResult"
})
@XmlRootElement(name = "QuerySubDeptResponse")
public class QuerySubDeptResponse {

    @XmlElement(name = "QuerySubDeptResult")
    protected ArrayOfDepartment querySubDeptResult;

    /**
     * Gets the value of the querySubDeptResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDepartment }
     *     
     */
    public ArrayOfDepartment getQuerySubDeptResult() {
        return querySubDeptResult;
    }

    /**
     * Sets the value of the querySubDeptResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDepartment }
     *     
     */
    public void setQuerySubDeptResult(ArrayOfDepartment value) {
        this.querySubDeptResult = value;
    }

}
