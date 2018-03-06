
package com.bitauto.bdc.modules.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmployeeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DomainAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NickName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EmployeeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PostTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Telephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Department" type="{http://tempuri.org/}Department" minOccurs="0"/>
 *         &lt;element name="Seat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsActive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TitleRank" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ManagerNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DimissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EntryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="OfficePlaceID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OfficePlace" type="{http://tempuri.org/}OfficePlace" minOccurs="0"/>
 *         &lt;element name="ServiceLengthStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeriateWorkStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegularWorkDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastWorkDimissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WorkCharacter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BirthDay" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="IDType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="IDCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Employee", propOrder = {
    "employeeID",
    "domainAccount",
    "password",
    "cnName",
    "enName",
    "nickName",
    "employeeNumber",
    "postTitle",
    "email",
    "telephone",
    "mobile",
    "department",
    "seat",
    "isActive",
    "titleRank",
    "managerNumber",
    "dimissionDate",
    "entryDate",
    "officePlaceID",
    "officePlace",
    "serviceLengthStartTime",
    "seriateWorkStartTime",
    "regularWorkDate",
    "lastWorkDimissionDate",
    "workCharacter",
    "birthDay",
    "idType",
    "idCode",
    "sex"
})
public class Employee {

    @XmlElement(name = "EmployeeID")
    protected int employeeID;
    @XmlElement(name = "DomainAccount")
    protected String domainAccount;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "CnName")
    protected String cnName;
    @XmlElement(name = "EnName")
    protected String enName;
    @XmlElement(name = "NickName")
    protected String nickName;
    @XmlElement(name = "EmployeeNumber")
    protected String employeeNumber;
    @XmlElement(name = "PostTitle")
    protected String postTitle;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Telephone")
    protected String telephone;
    @XmlElement(name = "Mobile")
    protected String mobile;
    @XmlElement(name = "Department")
    protected Department department;
    @XmlElement(name = "Seat")
    protected String seat;
    @XmlElement(name = "IsActive")
    protected boolean isActive;
    @XmlElement(name = "TitleRank")
    protected int titleRank;
    @XmlElement(name = "ManagerNumber")
    protected String managerNumber;
    @XmlElement(name = "DimissionDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dimissionDate;
    @XmlElement(name = "EntryDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entryDate;
    @XmlElement(name = "OfficePlaceID")
    protected int officePlaceID;
    @XmlElement(name = "OfficePlace")
    protected OfficePlace officePlace;
    @XmlElement(name = "ServiceLengthStartTime")
    protected String serviceLengthStartTime;
    @XmlElement(name = "SeriateWorkStartTime")
    protected String seriateWorkStartTime;
    @XmlElement(name = "RegularWorkDate")
    protected String regularWorkDate;
    @XmlElement(name = "LastWorkDimissionDate", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastWorkDimissionDate;
    @XmlElement(name = "WorkCharacter")
    protected int workCharacter;
    @XmlElement(name = "BirthDay", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthDay;
    @XmlElement(name = "IDType")
    protected int idType;
    @XmlElement(name = "IDCode")
    protected String idCode;
    @XmlElement(name = "Sex")
    protected int sex;

    /**
     * Gets the value of the employeeID property.
     * 
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     */
    public void setEmployeeID(int value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the domainAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainAccount() {
        return domainAccount;
    }

    /**
     * Sets the value of the domainAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainAccount(String value) {
        this.domainAccount = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the cnName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * Sets the value of the cnName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCnName(String value) {
        this.cnName = value;
    }

    /**
     * Gets the value of the enName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnName() {
        return enName;
    }

    /**
     * Sets the value of the enName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnName(String value) {
        this.enName = value;
    }

    /**
     * Gets the value of the nickName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the value of the nickName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * Gets the value of the employeeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * Sets the value of the employeeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeNumber(String value) {
        this.employeeNumber = value;
    }

    /**
     * Gets the value of the postTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the value of the postTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostTitle(String value) {
        this.postTitle = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the department property.
     * 
     * @return
     *     possible object is
     *     {@link Department }
     *     
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     * @param value
     *     allowed object is
     *     {@link Department }
     *     
     */
    public void setDepartment(Department value) {
        this.department = value;
    }

    /**
     * Gets the value of the seat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeat() {
        return seat;
    }

    /**
     * Sets the value of the seat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeat(String value) {
        this.seat = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     */
    public void setIsActive(boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the titleRank property.
     * 
     */
    public int getTitleRank() {
        return titleRank;
    }

    /**
     * Sets the value of the titleRank property.
     * 
     */
    public void setTitleRank(int value) {
        this.titleRank = value;
    }

    /**
     * Gets the value of the managerNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagerNumber() {
        return managerNumber;
    }

    /**
     * Sets the value of the managerNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagerNumber(String value) {
        this.managerNumber = value;
    }

    /**
     * Gets the value of the dimissionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDimissionDate() {
        return dimissionDate;
    }

    /**
     * Sets the value of the dimissionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDimissionDate(XMLGregorianCalendar value) {
        this.dimissionDate = value;
    }

    /**
     * Gets the value of the entryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntryDate() {
        return entryDate;
    }

    /**
     * Sets the value of the entryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntryDate(XMLGregorianCalendar value) {
        this.entryDate = value;
    }

    /**
     * Gets the value of the officePlaceID property.
     * 
     */
    public int getOfficePlaceID() {
        return officePlaceID;
    }

    /**
     * Sets the value of the officePlaceID property.
     * 
     */
    public void setOfficePlaceID(int value) {
        this.officePlaceID = value;
    }

    /**
     * Gets the value of the officePlace property.
     * 
     * @return
     *     possible object is
     *     {@link OfficePlace }
     *     
     */
    public OfficePlace getOfficePlace() {
        return officePlace;
    }

    /**
     * Sets the value of the officePlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfficePlace }
     *     
     */
    public void setOfficePlace(OfficePlace value) {
        this.officePlace = value;
    }

    /**
     * Gets the value of the serviceLengthStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceLengthStartTime() {
        return serviceLengthStartTime;
    }

    /**
     * Sets the value of the serviceLengthStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceLengthStartTime(String value) {
        this.serviceLengthStartTime = value;
    }

    /**
     * Gets the value of the seriateWorkStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriateWorkStartTime() {
        return seriateWorkStartTime;
    }

    /**
     * Sets the value of the seriateWorkStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriateWorkStartTime(String value) {
        this.seriateWorkStartTime = value;
    }

    /**
     * Gets the value of the regularWorkDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegularWorkDate() {
        return regularWorkDate;
    }

    /**
     * Sets the value of the regularWorkDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegularWorkDate(String value) {
        this.regularWorkDate = value;
    }

    /**
     * Gets the value of the lastWorkDimissionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastWorkDimissionDate() {
        return lastWorkDimissionDate;
    }

    /**
     * Sets the value of the lastWorkDimissionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastWorkDimissionDate(XMLGregorianCalendar value) {
        this.lastWorkDimissionDate = value;
    }

    /**
     * Gets the value of the workCharacter property.
     * 
     */
    public int getWorkCharacter() {
        return workCharacter;
    }

    /**
     * Sets the value of the workCharacter property.
     * 
     */
    public void setWorkCharacter(int value) {
        this.workCharacter = value;
    }

    /**
     * Gets the value of the birthDay property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the value of the birthDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDay(XMLGregorianCalendar value) {
        this.birthDay = value;
    }

    /**
     * Gets the value of the idType property.
     * 
     */
    public int getIDType() {
        return idType;
    }

    /**
     * Sets the value of the idType property.
     * 
     */
    public void setIDType(int value) {
        this.idType = value;
    }

    /**
     * Gets the value of the idCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDCode() {
        return idCode;
    }

    /**
     * Sets the value of the idCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDCode(String value) {
        this.idCode = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     */
    public int getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     */
    public void setSex(int value) {
        this.sex = value;
    }

}
