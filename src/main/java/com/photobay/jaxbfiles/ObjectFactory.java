//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.05.17 um 03:32:54 PM CEST 
//


package main.java.com.photobay.jaxbfiles;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the main.java.com.photobay.jaxbfiles package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: main.java.com.photobay.jaxbfiles
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GeneralPersonalDataType }
     * 
     */
    public GeneralPersonalDataType createGeneralPersonalDataType() {
        return new GeneralPersonalDataType();
    }

    /**
     * Create an instance of {@link PressAgency }
     * 
     */
    public PressAgency createPressAgency() {
        return new PressAgency();
    }

    /**
     * Create an instance of {@link GeneralPersonalDataType.Address }
     * 
     */
    public GeneralPersonalDataType.Address createGeneralPersonalDataTypeAddress() {
        return new GeneralPersonalDataType.Address();
    }

}