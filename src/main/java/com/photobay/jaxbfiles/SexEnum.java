
package main.java.com.photobay.jaxbfiles;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für sexEnum.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="sexEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="m"/>
 *     &lt;enumeration value="w"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sexEnum")
@XmlEnum
public enum SexEnum {

    @XmlEnumValue("m")
    M("m"),
    @XmlEnumValue("w")
    W("w");
    private final String value;

    SexEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SexEnum fromValue(String v) {
        for (SexEnum c: SexEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
