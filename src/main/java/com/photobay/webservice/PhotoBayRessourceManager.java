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
	 * This Method returns the Photographer Object with ID "id".
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
			JAXBContext context = JAXBContext.newInstance(PressAgency.class);
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
			File dir = new File("./host/pressAgencies/" + id);
			File file = new File(dir + "/PressAgency.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance(PressAgency.class);
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

	//TODO
	public static Boolean postBid(Bid bid) {
//			try
//			{
//				int id = IdGenerator.generateID("bids");
//				File dir = new File("./host/pressAgency/" + id);
//				File file = new File(dir + "/PressAgency.xml");
//				if(dir.mkdir() && file.createNewFile())
//				{
//					JAXBContext context = JAXBContext.newInstance("main.java.com.photobay.webservice");
//				    Marshaller m = context.createMarshaller();
//				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//				    pressAgency.setID(id);
//				    m.marshal(pressAgency, file);
//				    return true;
//				}
//				return false;
//			}
//			catch(Exception ex)
//			{
//				return false;
//			}
		return true;
	}
	//TODO
	public static Bid getBid(int id) {
		return null;
	}
	/**
	 * 
	 * @param job
	 * @return
	 */
	public static Boolean postJob(Job job){
			try
			{
				int id = IdGenerator.generateID("jobs");
				File dir = new File("./host/jobs/" + id);
				File file = new File(dir + "/job.xml");
				if(dir.mkdir() && file.createNewFile())
				{
					JAXBContext context = JAXBContext.newInstance(Job.class);
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				    job.setID(id);
				    m.marshal(job, file);
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
	public static Job getJob(int id) {
			try
			{
				File file = new File("./host/jobs/" + id + "/job.xml");
				JAXBContext context = JAXBContext.newInstance(Job.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				return (Job)unmarshaller.unmarshal(file);
			}
			catch(Exception ex)
			{
				return null;
			}
	}
	
	//TODO
	public static Boolean postJobApplication(JobApplication jobApplication){
		return true;
	}
	//TODO
	public static JobApplication getjobApplication(int id) {
		return null;
	}
	
	/**
	 * 
	 * @param photoSell
	 * @return
	 */
	public static Boolean postPhotoSell(PhotoSell photoSell){
		try
		{
			int id = IdGenerator.generateID("photoSells");
			File dir = new File("./host/photoSells/" + id);
			File file = new File(dir + "/photoSell.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance(PhotoSell.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    photoSell.setID(id);
			    m.marshal(photoSell, file);
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
	public static PhotoSell getPhotoSell(int id) {
		try
		{
			File file = new File("./host/photoSells/" + id + "/photoSell.xml");
			JAXBContext context = JAXBContext.newInstance(PhotoSell.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (PhotoSell)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Boolean deletePhotoSell(int id){
		File dir = new File("./host/photoSellss/" + id);
		File file = new File("./host/photoSell/" + id + "/photoSell.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}
	
}
