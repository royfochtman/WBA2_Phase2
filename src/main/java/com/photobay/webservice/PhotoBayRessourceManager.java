package main.java.com.photobay.webservice;
import main.java.com.photobay.jaxbfiles.*;
import java.io.File;
import java.util.Random;
import javax.xml.bind.*;
import com.sun.xml.bind.v2.TODO;


/**
 * 
 * @author David Wachs, Roy Fochtman
 *
 * This class access the xml files and forwards the data to the PhotoBayService
 */
public class PhotoBayRessourceManager {
	
	/**
	 * This Method returns the Photographer Object mit ID "id".
	 * 
	 * @param id
	 * @return Photographer
	 */
	public static Photographer getPhotographer(int id)
	{
		try
		{
			File file = new File("./host/photographers/" + id + "/photographer.xml");
			JAXBContext context = JAXBContext.newInstance(Photographer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Photographer)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/**
	 * Method for setting a new Photographer
	 * 
	 * @param photographer
	 * @return
	 */
	public static Boolean postPhotographer(Photographer photographer)
	{
		try
		{
			int id = IdGenerator.generateID("photographers");
			File dir = new File("./host/photographers/" + id);
			File file = new File(dir + "/photographer.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance(Photographer.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    photographer.setID(id);
			    m.marshal(photographer, file);
			    return true;
			}
			return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Boolean deletePhotographer(int id)
	{
		//oder muss auch Ordner gelöscht werden???
		File dir = new File("./host/photographers/" + id);
		File file = new File("./host/photographers/" + id + "/photographer.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static PressAgency getPressAgency(int id)
	{
		try
		{
			File file = new File("./host/pressAgencies/" + id + "/pressAgency.xml");
			JAXBContext context = JAXBContext.newInstance("main.java.com.photobay.webservice");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (PressAgency)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/**
	 * 
	 * @param pressAgency
	 * @return
	 */
	public static Boolean postPressAgency(PressAgency pressAgency)
	{
		try
		{
			int id = IdGenerator.generateID("pressAgencies");
			File dir = new File("./host/pressAgency/" + id);
			File file = new File(dir + "/PressAgency.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance("main.java.com.photobay.webservice");
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    pressAgency.setID(id);
			    m.marshal(pressAgency, file);
			    return true;
			}
			return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Boolean deletePressAgency(int id)
	{
		File dir = new File("./host/pressAgency/" + id);
		File file = new File("./host/pressAgency/" + id + "/pressAgency.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}

}
