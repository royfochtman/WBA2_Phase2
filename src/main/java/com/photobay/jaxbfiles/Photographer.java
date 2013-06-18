//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.15 um 03:35:52 PM CEST 
//


package main.java.com.photobay.jaxbfiles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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
 *       &lt;all minOccurs="0">
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="sex" type="{http://www.example.org/photoBay}sexEnum"/>
 *         &lt;element name="equipment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="generalPersonalData" type="{http://www.example.org/photoBay}generalPersonalDataType"/>
 *       &lt;/all>
 *       &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="photosRef" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="photoSellsRef" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
@XmlRootElement(name = "photographer")
public class Photographer {

    protected String firstname;
    protected String lastname;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthdate;
    protected SexEnum sex;
    protected String equipment;
    protected GeneralPersonalDataType generalPersonalData;
    @XmlAttribute(name = "ref", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String ref;
    @XmlAttribute(name = "ID", required = true)
    protected int id;
    @XmlAttribute(name = "photosRef", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String photosRef;
    @XmlAttribute(name = "photoSellsRef", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String photoSellsRef;
    @XmlAttribute(name = "registrationDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar registrationDate;
    

    public Photographer(SexEnum sex, String firstName, String lastName, String username, int[] birthdate, String street, String houseNumber,
    		int postalCode, String city, String country, String email)
    {
    	this.sex = sex;
    	this.firstname = firstName;
    	this.lastname = lastName;
    	try
    	{
    		this.birthdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(birthdate[0], 
	    			birthdate[1], birthdate[2], 0, 0, 0, 0, 0);
    	}catch(DatatypeConfigurationException ex){}
    	GeneralPersonalDataType gd = new GeneralPersonalDataType(username, email, street, houseNumber, postalCode, city, country);
    	this.generalPersonalData = gd;
    }

    /**
     * Ruft den Wert der firstname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Legt den Wert der firstname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Ruft den Wert der lastname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Legt den Wert der lastname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Ruft den Wert der birthdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthdate() {
        return birthdate;
    }

    /**
     * Legt den Wert der birthdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthdate(XMLGregorianCalendar value) {
        this.birthdate = value;
    }

    /**
     * Ruft den Wert der sex-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SexEnum }
     *     
     */
    public SexEnum getSex() {
        return sex;
    }

    /**
     * Legt den Wert der sex-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SexEnum }
     *     
     */
    public void setSex(SexEnum value) {
        this.sex = value;
    }

    /**
     * Ruft den Wert der equipment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Legt den Wert der equipment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipment(String value) {
        this.equipment = value;
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
     * Ruft den Wert der photoSellsRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhotoSellsRef() {
        return photoSellsRef;
    }

    /**
     * Legt den Wert der photoSellsRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhotoSellsRef(String value) {
        this.photoSellsRef = value;
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
