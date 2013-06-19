//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.19 um 11:24:57 AM CEST 
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
 *         &lt;element name="yearOfEstablishment" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="generalPersonalData" type="{http://www.example.org/photoBay}generalPersonalDataType"/>
 *       &lt;/all>
 *       &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="photosRef" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="jobsRef" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
	
	public PressAgency(){};
	
	public PressAgency(String username, String name, String mainLocation, int yearOfEstablishment, String street, String houseNumber,
    		int postalCode, String city, String country, String email)
	{
		this.name = name;
		this.mainLocation = mainLocation;
		this.yearOfEstablishment = yearOfEstablishment;
		GeneralPersonalDataType gt = new GeneralPersonalDataType(username, email, street, houseNumber, postalCode, city, country);
		this.generalPersonalData = gt;
	}

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String mainLocation;
    protected int yearOfEstablishment;
    @XmlElement(required = true)
    protected GeneralPersonalDataType generalPersonalData;
    @XmlAttribute(name = "ref", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String ref;
    @XmlAttribute(name = "ID", required = true)
    protected int id;
    @XmlAttribute(name = "photosRef")
    @XmlSchemaType(name = "anyURI")
    protected String photosRef;
    @XmlAttribute(name = "jobsRef")
    @XmlSchemaType(name = "anyURI")
    protected String jobsRef;
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
     * Ruft den Wert der yearOfEstablishment-Eigenschaft ab.
     * 
     */
    public int getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    /**
     * Legt den Wert der yearOfEstablishment-Eigenschaft fest.
     * 
     */
    public void setYearOfEstablishment(int value) {
        this.yearOfEstablishment = value;
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
     * Ruft den Wert der ref-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Legt den Wert der ref-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
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
     * Ruft den Wert der photosRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhotosRef() {
        return photosRef;
    }

    /**
     * Legt den Wert der photosRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhotosRef(String value) {
        this.photosRef = value;
    }

    /**
     * Ruft den Wert der jobsRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobsRef() {
        return jobsRef;
    }

    /**
     * Legt den Wert der jobsRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobsRef(String value) {
        this.jobsRef = value;
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
