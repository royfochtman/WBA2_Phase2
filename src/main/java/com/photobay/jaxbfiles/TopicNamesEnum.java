//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.20 um 04:16:21 PM CEST 
//


package main.java.com.photobay.jaxbfiles;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für topicNamesEnum.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="topicNamesEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Sport"/>
 *     &lt;enumeration value="Politic"/>
 *     &lt;enumeration value="People"/>
 *     &lt;enumeration value="Nature"/>
 *     &lt;enumeration value="Special"/>
 *     &lt;enumeration value="Economy"/>
 *     &lt;enumeration value="War"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "topicNamesEnum")
@XmlEnum
public enum TopicNamesEnum {

    @XmlEnumValue("Sport")
    SPORT("Sport"),
    @XmlEnumValue("Politic")
    POLITIC("Politic"),
    @XmlEnumValue("People")
    PEOPLE("People"),
    @XmlEnumValue("Nature")
    NATURE("Nature"),
    @XmlEnumValue("Special")
    SPECIAL("Special"),
    @XmlEnumValue("Economy")
    ECONOMY("Economy"),
    @XmlEnumValue("War")
    WAR("War");
    private final String value;

    TopicNamesEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TopicNamesEnum fromValue(String v) {
        for (TopicNamesEnum c: TopicNamesEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
