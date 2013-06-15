//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.15 um 02:15:35 PM CEST 
//


package main.java.com.photobay.jaxbfiles;

import java.math.BigInteger;
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
 *         &lt;element name="jobName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urgency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deadline" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payment" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="topics" type="{http://www.example.org/photoBay}topicType"/>
 *         &lt;element name="status">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="new"/>
 *               &lt;enumeration value="assigned"/>
 *               &lt;enumeration value="closed"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/all>
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ref" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="pressAgencyRef" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="dateOfCreation" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
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
@XmlRootElement(name = "job")
public class Job {

    @XmlElement(required = true)
    protected String jobName;
    @XmlElement(required = true)
    protected String urgency;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deadline;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger payment;
    @XmlElement(required = true)
    protected TopicType topics;
    @XmlElement(required = true)
    protected String status;
    @XmlAttribute(name = "ID", required = true)
    protected int id;
    @XmlAttribute(name = "ref", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String ref;
    @XmlAttribute(name = "pressAgencyRef", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String pressAgencyRef;
    @XmlAttribute(name = "dateOfCreation", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfCreation;

    /**
     * Ruft den Wert der jobName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Legt den Wert der jobName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobName(String value) {
        this.jobName = value;
    }

    /**
     * Ruft den Wert der urgency-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrgency() {
        return urgency;
    }

    /**
     * Legt den Wert der urgency-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrgency(String value) {
        this.urgency = value;
    }

    /**
     * Ruft den Wert der deadline-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeadline() {
        return deadline;
    }

    /**
     * Legt den Wert der deadline-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeadline(XMLGregorianCalendar value) {
        this.deadline = value;
    }

    /**
     * Ruft den Wert der description-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Legt den Wert der description-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Ruft den Wert der payment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPayment() {
        return payment;
    }

    /**
     * Legt den Wert der payment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPayment(BigInteger value) {
        this.payment = value;
    }

    /**
     * Ruft den Wert der topics-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TopicType }
     *     
     */
    public TopicType getTopics() {
        return topics;
    }

    /**
     * Legt den Wert der topics-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TopicType }
     *     
     */
    public void setTopics(TopicType value) {
        this.topics = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     * Ruft den Wert der pressAgencyRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPressAgencyRef() {
        return pressAgencyRef;
    }

    /**
     * Legt den Wert der pressAgencyRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPressAgencyRef(String value) {
        this.pressAgencyRef = value;
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

}
