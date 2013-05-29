//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.29 um 02:09:09 PM CEST 
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
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="photoSellID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="pressID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="photographerID" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    @XmlAttribute(name = "ID")
    protected Integer id;
    @XmlAttribute(name = "photoSellID", required = true)
    protected int photoSellID;
    @XmlAttribute(name = "pressID", required = true)
    protected int pressID;
    @XmlAttribute(name = "photographerID", required = true)
    protected int photographerID;

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
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getID() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setID(Integer value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der photoSellID-Eigenschaft ab.
     * 
     */
    public int getPhotoSellID() {
        return photoSellID;
    }

    /**
     * Legt den Wert der photoSellID-Eigenschaft fest.
     * 
     */
    public void setPhotoSellID(int value) {
        this.photoSellID = value;
    }

    /**
     * Ruft den Wert der pressID-Eigenschaft ab.
     * 
     */
    public int getPressID() {
        return pressID;
    }

    /**
     * Legt den Wert der pressID-Eigenschaft fest.
     * 
     */
    public void setPressID(int value) {
        this.pressID = value;
    }

    /**
     * Ruft den Wert der photographerID-Eigenschaft ab.
     * 
     */
    public int getPhotographerID() {
        return photographerID;
    }

    /**
     * Legt den Wert der photographerID-Eigenschaft fest.
     * 
     */
    public void setPhotographerID(int value) {
        this.photographerID = value;
    }

}
