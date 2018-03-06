
package com.bitauto.bdc.modules.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for OfficePlace complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfficePlace">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PlaceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SortNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CreateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CreateUserID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FullPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfficePlace", propOrder = {
    "recID",
    "placeName",
    "level",
    "pid",
    "sortNumber",
    "status",
    "createTime",
    "createUserID",
    "fullPath"
})
public class OfficePlace {

    @XmlElement(name = "RecID")
    protected int recID;
    @XmlElement(name = "PlaceName")
    protected String placeName;
    @XmlElement(name = "Level", required = true, type = Integer.class, nillable = true)
    protected Integer level;
    @XmlElement(name = "PID")
    protected int pid;
    @XmlElement(name = "SortNumber", required = true, type = Integer.class, nillable = true)
    protected Integer sortNumber;
    @XmlElement(name = "Status", required = true, type = Integer.class, nillable = true)
    protected Integer status;
    @XmlElement(name = "CreateTime", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createTime;
    @XmlElement(name = "CreateUserID", required = true, type = Integer.class, nillable = true)
    protected Integer createUserID;
    @XmlElement(name = "FullPath")
    protected String fullPath;

    /**
     * Gets the value of the recID property.
     * 
     */
    public int getRecID() {
        return recID;
    }

    /**
     * Sets the value of the recID property.
     * 
     */
    public void setRecID(int value) {
        this.recID = value;
    }

    /**
     * Gets the value of the placeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceName() {
        return placeName;
    }

    /**
     * Sets the value of the placeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceName(String value) {
        this.placeName = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLevel(Integer value) {
        this.level = value;
    }

    /**
     * Gets the value of the pid property.
     * 
     */
    public int getPID() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     */
    public void setPID(int value) {
        this.pid = value;
    }

    /**
     * Gets the value of the sortNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * Sets the value of the sortNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSortNumber(Integer value) {
        this.sortNumber = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(XMLGregorianCalendar value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the createUserID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCreateUserID() {
        return createUserID;
    }

    /**
     * Sets the value of the createUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCreateUserID(Integer value) {
        this.createUserID = value;
    }

    /**
     * Gets the value of the fullPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * Sets the value of the fullPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullPath(String value) {
        this.fullPath = value;
    }

}
