//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.15 um 03:39:57 PM CEST 
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
 *         &lt;element name="bidValue" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/all>
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="photoSellRef" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="pressAgencyRef" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
@XmlRootElement(name = "bid")
public class Bid {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger bidValue;
    @XmlAttribute(name = "ID", required = true)
    protected int id;
    @XmlAttribute(name = "ref")
    @XmlSchemaType(name = "anyURI")
    protected String ref;
    @XmlAttribute(name = "photoSellRef", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String photoSellRef;
    @XmlAttribute(name = "pressAgencyRef", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String pressAgencyRef;

    /**
     * Ruft den Wert der bidValue-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBidValue() {
        return bidValue;
    }

    /**
     * Legt den Wert der bidValue-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBidValue(BigInteger value) {
        this.bidValue = value;
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
     * Ruft den Wert der photoSellRef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhotoSellRef() {
        return photoSellRef;
    }

    /**
     * Legt den Wert der photoSellRef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhotoSellRef(String value) {
        this.photoSellRef = value;
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

}
