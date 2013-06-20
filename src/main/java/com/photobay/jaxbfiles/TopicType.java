//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.20 um 04:16:21 PM CEST 
//


package main.java.com.photobay.jaxbfiles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für topicType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="topicType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="topicName" type="{http://www.example.org/photoBay}topicNamesEnum"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "topicType", propOrder = {
    "topicName"
})
public class TopicType {

    @XmlElement(required = true)
    protected TopicNamesEnum topicName;

    /**
     * Ruft den Wert der topicName-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TopicNamesEnum }
     *     
     */
    public TopicNamesEnum getTopicName() {
        return topicName;
    }

    /**
     * Legt den Wert der topicName-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TopicNamesEnum }
     *     
     */
    public void setTopicName(TopicNamesEnum value) {
        this.topicName = value;
    }

}
