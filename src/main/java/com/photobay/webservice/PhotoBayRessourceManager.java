package main.java.com.photobay.webservice;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import main.java.com.photobay.jaxbfiles.Bid;
import main.java.com.photobay.jaxbfiles.Job;
import main.java.com.photobay.jaxbfiles.JobApplication;
import main.java.com.photobay.jaxbfiles.PhotoSell;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.PressAgency;


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
				File subDir = new File("./host/photographers/" + id + "/photos");
				subDir.mkdir();
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
				dir = new File("./host/pressAgencies/" + id + "/photos");
				dir.mkdir();
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
		File dir = new File("./host/pressAgencies/" + id);
		File file = new File("./host/pressAgencies/" + id + "/pressAgency.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}

	public static Boolean postBid(Bid bid, int photoSellID) {
			try
			{
				int id = IdGenerator.generateID("photoSells/" + photoSellID + "/bids");
				File dir = new File("./host/photoSells/" + photoSellID + "/bids/" + id );
				File file = new File(dir + "/Bid.xml");
				if(dir.mkdir() && file.createNewFile())
				{
					JAXBContext context = JAXBContext.newInstance(Bid.class);
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				    bid.setID(id);
				    m.marshal(bid, file);
				    return true;
				}
				return false;
			}
			catch(Exception ex)
			{
				return false;
			}
	}

	public static Bid getBid(int photoSellsID, int bidID) {
		try
		{
			File file = new File("./host/photoSells/" + photoSellsID + "/bids/" + bidID + "/bid.xml");
			JAXBContext context = JAXBContext.newInstance(Bid.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Bid)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
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
					dir = new File("./host/jobs/" + id + "/jobApplications");
					dir.mkdir();
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
	
	public static Boolean postJobApplication(JobApplication jobApplication, int jobID){
		try
		{
			int id = IdGenerator.generateID("jobs/" + jobID + "/jobApplications");
			File dir = new File("./host/jobs/" + jobID + "/jobApplications/" + id);
			File file = new File(dir + "/jobApplication.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance(JobApplication.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    jobApplication.setID(id);
			    m.marshal(jobApplication, file);
			    return true;
			}
			return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static JobApplication getJobApplication(int jobID, int jobApplicationID) {
		try
		{
			File file = new File("./host/jobs/" + jobID + "/jobApplications/" + jobApplicationID + "/jobApplication.xml");
			JAXBContext context = JAXBContext.newInstance(JobApplication.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (JobApplication)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}

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
				dir = new File("./host/photoSells/" + id + "/bids");
				dir.mkdir();
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
		File dir = new File("./host/photoSells/" + id);
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
