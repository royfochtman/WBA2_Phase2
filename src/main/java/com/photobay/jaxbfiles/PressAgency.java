//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.17 um 06:06:42 PM CEST 
//


package main.java.com.photobay.jaxbfiles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mainLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateOfCreation" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="generalPersonalData" type="{http://www.example.org/photoBay}generalPersonalDataType"/>
 *       &lt;/all>
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="registrationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "pressAgency")
public class PressAgency {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String mainLocation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfCreation;
    @XmlElement(required = true)
    protected GeneralPersonalDataType generalPersonalData;
    @XmlAttribute(name = "ID", required = true)
    protected int id;
    @XmlAttribute(name = "registrationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar registrationDate;

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der mainLocation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainLocation() {
        return mainLocation;
    }

    /**
     * Legt den Wert der mainLocation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainLocation(String value) {
        this.mainLocation = value;
    }

    /**
     * Ruft den Wert der dateOfCreation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Legt den Wert der dateOfCreation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfCreation(XMLGregorianCalendar value) {
        this.dateOfCreation = value;
    }

    /**
     * Ruft den Wert der generalPersonalData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link GeneralPersonalDataType }
     *     
     */
    public GeneralPersonalDataType getGeneralPersonalData() {
        return generalPersonalData;
    }

    /**
     * Legt den Wert der generalPersonalData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralPersonalDataType }
     *     
     */
    public void setGeneralPersonalData(GeneralPersonalDataType value) {
        this.generalPersonalData = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der registrationDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Legt den Wert der registrationDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegistrationDate(XMLGregorianCalendar value) {
        this.registrationDate = value;
    }

}
