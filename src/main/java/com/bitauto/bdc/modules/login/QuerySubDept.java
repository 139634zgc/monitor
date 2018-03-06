
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
 *         &lt;element name="deptId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="includeAll" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "deptId",
    "includeAll"
})
@XmlRootElement(name = "QuerySubDept")
public class QuerySubDept {

    protected int deptId;
    protected boolean includeAll;

    /**
     * Gets the value of the deptId property.
     * 
     */
    public int getDeptId() {
        return deptId;
    }

    /**
     * Sets the value of the deptId property.
     * 
     */
    public void setDeptId(int value) {
        this.deptId = value;
    }

    /**
     * Gets the value of the includeAll property.
     * 
     */
    public boolean isIncludeAll() {
        return includeAll;
    }

    /**
     * Sets the value of the includeAll property.
     * 
     */
    public void setIncludeAll(boolean value) {
        this.includeAll = value;
    }

}
