
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
 *         &lt;element name="includeChildren" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="filterParttime" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pageIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "includeChildren",
    "filterParttime",
    "pageSize",
    "pageIndex"
})
@XmlRootElement(name = "QueryEmployeeByDept")
public class QueryEmployeeByDept {

    protected int deptId;
    protected boolean includeChildren;
    protected boolean filterParttime;
    protected int pageSize;
    protected int pageIndex;

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
     * Gets the value of the includeChildren property.
     * 
     */
    public boolean isIncludeChildren() {
        return includeChildren;
    }

    /**
     * Sets the value of the includeChildren property.
     * 
     */
    public void setIncludeChildren(boolean value) {
        this.includeChildren = value;
    }

    /**
     * Gets the value of the filterParttime property.
     * 
     */
    public boolean isFilterParttime() {
        return filterParttime;
    }

    /**
     * Sets the value of the filterParttime property.
     * 
     */
    public void setFilterParttime(boolean value) {
        this.filterParttime = value;
    }

    /**
     * Gets the value of the pageSize property.
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the value of the pageSize property.
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * Gets the value of the pageIndex property.
     * 
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * Sets the value of the pageIndex property.
     * 
     */
    public void setPageIndex(int value) {
        this.pageIndex = value;
    }

}
