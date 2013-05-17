package main.java.com.photobay.webservice;

import main.java.com.photobay.jaxbfiles.*;

import java.io.File;
import java.util.Random;

import javax.xml.bind.*;


/**
 * 
 * @author David Wachs, Roy Fochtman
 *
 * This class access the xml files and forwards the data to the PhotoBayService
 */
public class PhotoBayRessourceManager {
	
	static Random generator = new Random();
	
	private static int generateID()
	{
		return generator.nextInt();
	}
	
	public static Photographer getPhotographer(int id)
	{
		try
		{
			File file = new File("./host/photographers/" + id + "/photographer.xml");
			JAXBContext context = JAXBContext.newInstance("main.java.com.photobay.webservice");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Photographer)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static Boolean putPhotographer(Photographer photographer)
	{
		try
		{
			int id = generateID();
			File dir = new File("./host/photographers/" + id);
			File file = new File(dir + "/photographer.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance("main.java.com.photobay.webservice");
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
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
}
