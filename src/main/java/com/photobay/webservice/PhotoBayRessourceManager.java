package main.java.com.photobay.webservice;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import main.java.com.photobay.jaxbfiles.Bid;
import main.java.com.photobay.jaxbfiles.Bids;
import main.java.com.photobay.jaxbfiles.Bids.BidRef;
import main.java.com.photobay.jaxbfiles.Job;
import main.java.com.photobay.jaxbfiles.JobApplication;
import main.java.com.photobay.jaxbfiles.Jobs;
import main.java.com.photobay.jaxbfiles.Jobs.JobRef;
import main.java.com.photobay.jaxbfiles.Photo;
import main.java.com.photobay.jaxbfiles.PhotoSell;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.Photographers;
import main.java.com.photobay.jaxbfiles.Photographers.PhotographerRef;
import main.java.com.photobay.jaxbfiles.Photos.PhotoRef;
import main.java.com.photobay.jaxbfiles.PressAgencies.PressAgencyRef;
import main.java.com.photobay.jaxbfiles.Photos;
import main.java.com.photobay.jaxbfiles.PressAgencies;
import main.java.com.photobay.jaxbfiles.PressAgency;
import main.java.com.photobay.util.IdGenerator;


/**
 * 
 * @author David Wachs, Roy Fochtman
 *
 * This class access the xml files and forwards the data to the PhotoBayService
 */
public class PhotoBayRessourceManager {
	
	/**
	 * Gets existing Photographer.
	 * 
	 * @param id ID from the Photographer
	 * @return Photographer with ID "id".
	 */
	public static Photographer getPhotographer(int id)
	{
		try
		{
			File file = new File("./photographers/" + id + "/photographer.xml");
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
	 * Posts a new Photographer.
	 * 
	 * @param photographer Photographer object
	 * @return true if photograph was posted successfully, false if not
	 */
	public static Boolean postPhotographer(Photographer photographer)
	{
		try
		{
			int id = IdGenerator.generateID("photographers");
			File dir = new File("./photographers/" + id);
			File file = new File(dir + "/photographer.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				/*creates the folder for the photos*/
				File subDir = new File("./photographers/" + id + "/photos");
				subDir.mkdir();
				/* creates a new XML File for the photos, but empty */
				File photosXML = new File(subDir + "/photos.xml");
				photosXML.createNewFile();
				
				/* creates a new XML File for the PhotoSells*/
				File photoSellsDir = new File(dir + "/photoSells");
				photoSellsDir.mkdir();
				File photoSellsXML = new File(photoSellsDir + "/photoSells.xml");
				photoSellsXML.createNewFile();
				
				
				JAXBContext context = JAXBContext.newInstance(Photographer.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    
			    photographer.setID(id);
			    photographer.setRef(dir.toString());
			    photographer.setPhotosRef(subDir.toString());
			    photographer.setPhotoSellsRef(photoSellsDir.toString());
			    
			    m.marshal(photographer, file);
			    putPhotographersList(photographer);
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
	 * Deletes a photograph
	 * @param id id from photograph
	 * @return true if photograph was deleted successfully, false if not
	 */
	public static Boolean deletePhotographer(int id)
	{
		File dir = new File("./photographers/" + id);
		File file = new File("./photographers/" + id + "/photographer.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}

	/**
	 * Get existing PressAgency
	 * 
	 * @param id ID from the PressAgency
	 * @return PressAgency object
	 */
	public static PressAgency getPressAgency(int id)
	{
		try
		{
			File file = new File("./pressAgencies/" + id + "/pressAgency.xml");
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
	 * Posts a new PressAgency
	 * 
	 * @param pressAgency
	 * @return true if PressAgency was posted successfully, false if not
	 */
	public static Boolean postPressAgency(PressAgency pressAgency)
	{
		try
		{
			int id = IdGenerator.generateID("pressAgencies");
			File dir = new File("./pressAgencies/" + id);
			File file = new File(dir + "/PressAgency.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				/* creates a new XML File for the photos */
				File photosDir = new File("./pressAgencies/" + id + "/photos");
				photosDir.mkdir();
				File photosXML = new File(photosDir + "/photos.xml");
				photosXML.createNewFile();
				
				/* creates a new XML File for Jobs */
				File jobsDir = new File(dir + "/jobs");
				jobsDir.mkdir();
				File jobsXML = new File(jobsDir + "/jobs.xml");
				jobsXML.createNewFile();
				
				JAXBContext context = JAXBContext.newInstance(PressAgency.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    pressAgency.setID(id);
			    /* Referenz zu sich selbst */
			    pressAgency.setRef(dir.toString());
			    pressAgency.setJobsRef(jobsDir.toString());
			    pressAgency.setPhotosRef(photosDir.toString());
			    
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
	 * Deletes an existing PressAgency
	 * 
	 * @param id
	 * @return
	 */
	public static Boolean deletePressAgency(int id)
	{
		File dir = new File("./pressAgencies/" + id);
		File file = new File("./pressAgencies/" + id + "/pressAgency.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}
	
	/**
	 * Posts a new Bid.
	 * 
	 * @param bid
	 * @param photoSellID
	 * @param pressAgencyRef
	 * @return
	 */
	public static Boolean postBid(Bid bid, int photoSellID, String pressAgencyRef ) {
			try
			{
				int id = IdGenerator.generateID("photoSells/" + photoSellID + "/bids");
				File dir = new File("./photoSells/" + photoSellID + "/bids/" + id );
				File file = new File(dir + "/Bid.xml");
				if(dir.mkdir() && file.createNewFile())
				{
					JAXBContext context = JAXBContext.newInstance(Bid.class);
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				    
				    bid.setID(id);
				    bid.setRef(dir.toString());
				    bid.setPhotoSellRef("./photoSells/" + photoSellID);
				    bid.setPressAgencyRef(pressAgencyRef);
				    
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

	/**
	 * Gets a Bid.
	 * 
	 * @param photoSellsID
	 * @param bidID
	 * @return
	 */
	public static Bid getBid(int photoSellsID, int bidID) {
		try
		{
			File file = new File("./photoSells/" + photoSellsID + "/bids/" + bidID + "/bid.xml");
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
	 * Posts a new Job
	 * 
	 * @param job
	 * @param pressAgencyRef
	 * @return
	 */
	public static Boolean postJob(Job job, String pressAgencyRef){
			try
			{
				int id = IdGenerator.generateID("jobs");
				File dir = new File("./jobs/" + id);
				File file = new File(dir + "/job.xml");
				if(dir.mkdir() && file.createNewFile())
				{
					dir = new File("./jobs/" + id + "/jobApplications");
					dir.mkdir();
					JAXBContext context = JAXBContext.newInstance(Job.class);
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				   
				    job.setID(id);
				    job.setRef(dir.toString());
				    job.setPressAgencyRef(pressAgencyRef);
				    
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
	 * Gets an existing Job.
	 * 
	 * @param id ID from the job.
	 * @return Job Object
	 */
	public static Job getJob(int id) {
			try
			{
				File file = new File("./jobs/" + id + "/job.xml");
				JAXBContext context = JAXBContext.newInstance(Job.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				return (Job)unmarshaller.unmarshal(file);
			}
			catch(Exception ex)
			{
				return null;
			}
	}
	
	/**
	 *  Posts a new JobApplication.
	 *  
	 * @param jobApplication
	 * @param jobID
	 * @param photographerRef
	 * @return true if JobApplication was posted successfully, false if not
	 */
	public static Boolean postJobApplication(JobApplication jobApplication, int jobID, String photographerRef){
		try
		{
			int id = IdGenerator.generateID("jobs/" + jobID + "/jobApplications");
			File dir = new File("./jobs/" + jobID + "/jobApplications/" + id);
			File file = new File(dir + "/jobApplication.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance(JobApplication.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    
			    jobApplication.setID(id);
			    jobApplication.setRef(dir.toString());
			    jobApplication.setJobRef("./jobs/" + jobID);
			    jobApplication.setPhotographerRef(photographerRef);
			    
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
	
	/**
	 * Gets an existing JobApplication.
	 * 
	 * @param jobID
	 * @param jobApplicationID
	 * @return
	 */
	public static JobApplication getJobApplication(int jobID, int jobApplicationID) {
		try
		{
			File file = new File("./jobs/" + jobID + "/jobApplications/" + jobApplicationID + "/jobApplication.xml");
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
	 * Posts a new PhotoSell.
	 * 
	 * @param photoSell
	 * @param photographerRef
	 * @return true if PhotoSell was posted successfully, false if not
	 */
	public static Boolean postPhotoSell(PhotoSell photoSell, String photographerRef){
		try
		{
			int id = IdGenerator.generateID("photoSells");
			File dir = new File("./photoSells/" + id);
			File file = new File(dir + "/photoSell.xml");
			if(dir.mkdir() && file.createNewFile())
			{
				dir = new File("./photoSells/" + id + "/bids");
				dir.mkdir();
				JAXBContext context = JAXBContext.newInstance(PhotoSell.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    
			    photoSell.setID(id);
			    photoSell.setRef(dir.toString());
			    photoSell.setBidsRef(dir.toString() + "/bids");
			    photoSell.setPhotographerRef(photographerRef);
			    
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
	 * Gets an existing PhotoSell.
	 * 
	 * @param id
	 * @return
	 */
	public static PhotoSell getPhotoSell(int id) {
		try
		{
			File file = new File("./photoSells/" + id + "/photoSell.xml");
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
	 * Deletes PhotoSell.
	 * 
	 * @param id
	 * @return
	 */
	public static Boolean deletePhotoSell(int id){
		File dir = new File("./photoSells/" + id);
		File file = new File("./photoSell/" + id + "/photoSell.xml");
		if(file.delete()){
			if(dir.delete())
				return true;
			else
				return false;
		}
		else return false;
	}
	
	
	/*********************************************
	 *
	 * Hier werden alle Listen verwaltet
	 * (PUT, GET)
	 * 
	 * 
	 *
	 *********************************************/
	
	/**
	 * Puts a list of Photographers.
	 * 
	 * @param photographer
	 * @return 
	 */
	public static Boolean putPhotographersList(Photographer photographer){
		try
		{
			File photographersListFile = new File("./photographers/photographers.xml");
		    Photographers photographersList;
		    if(photographersListFile.createNewFile())
		    	photographersList = new Photographers();
		    else
		    	photographersList = getPhotographersList();
		    
		    if(photographersList != null)
		    {
		    	PhotographerRef photographerRef = new PhotographerRef();
		    	photographerRef.setFirstName(photographer.getFirstname());
		    	photographerRef.setLastName(photographer.getLastname());
		    	//Muss nicht "./photographers/" sein ???
//		    	photographerRef.setUri("/photographers/" + photographer.getID());
		    	photographerRef.setUri(photographer.getRef());
		    	photographersList.getPhotographerRef().add(photographerRef);
		    }
			JAXBContext context = JAXBContext.newInstance(Photographers.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(photographersList, photographersListFile);
		    return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/**
	 * Gets List of photographers.
	 * 
	 * @return List of photographers.
	 */
	public static Photographers getPhotographersList() {
		try
		{
			File file = new File("./photographers/photographers.xml");
			JAXBContext context = JAXBContext.newInstance(Photographers.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Photographers)unmarshaller.unmarshal(file);
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
	public static Boolean putPressAgenciesList(PressAgency pressAgency){
		try
		{
			File pressAgenciesListFile = new File("./pressAgencies/pressAgencies.xml");
		    PressAgencies pressAgenciesList;
		    if(pressAgenciesListFile.createNewFile())
		    	pressAgenciesList = new PressAgencies();
		    else
		    	pressAgenciesList = getPressAgenciesList();
		    
		    if(pressAgenciesList != null)
		    {
		    	PressAgencyRef pressAgencyRef = new PressAgencyRef();
		    	pressAgencyRef.setName(pressAgency.getName());
		    	pressAgencyRef.setUri(pressAgency.getRef());
		    	pressAgenciesList.getPressAgencyRef().add(pressAgencyRef);
		    }
			JAXBContext context = JAXBContext.newInstance(PressAgencies.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(pressAgenciesList, pressAgenciesListFile);
		    return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	/**
	 * 
	 * @return
	 */
	public static PressAgencies getPressAgenciesList(){
		try
		{
			File file = new File("./pressAgencies/pressAgencies.xml");
			JAXBContext context = JAXBContext.newInstance(PressAgencies.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (PressAgencies)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	/**
	 * 
	 * @param bid
	 * @param photoSellRef
	 * @return
	 */
	public static Boolean putBidsList(Bid bid, String photoSellRef){
		try
		{
			File bidsListFile = new File(photoSellRef + "/bids/bids.xml");
		    Bids bidsList;
		    if(bidsListFile.createNewFile())
		    	bidsList = new Bids();
		    else
		    	bidsList = getBidsList(photoSellRef);
		    
		    if(bidsList != null)
		    {
		    	BidRef bidRef = new BidRef();
		    	bidRef.setValue(bid.getBidValue());
		    	bidRef.setUri(bid.getRef());
		    	bidsList.getBidRef().add(bidRef);
		    }
			JAXBContext context = JAXBContext.newInstance(Bids.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(bidsList, bidsListFile);
		    return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	/**
	 * 
	 * @param photoSellRef
	 * @return
	 */
	public static Bids getBidsList(String photoSellRef) {
		try
		{
			File file = new File(photoSellRef + "/bids/bids.xml");
			JAXBContext context = JAXBContext.newInstance(Bids.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Bids)unmarshaller.unmarshal(file);
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
	public static Boolean putJobsList(Job job){
		try
		{
			File jobsListFile = new File("./jobs/jobs.xml");
		    Jobs jobsList;
		    if(jobsListFile.createNewFile())
		    	jobsList = new Jobs();
		    else
		    	jobsList = getJobsList();
		    
		    if(jobsList != null)
		    {
		    	JobRef jobRef = new JobRef();
		    	jobRef.setJobName(job.getJobName());
		    	jobRef.setUri(job.getRef());
		    	jobsList.getJobRef().add(jobRef);
		    }
			JAXBContext context = JAXBContext.newInstance(Jobs.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(jobsList, jobsListFile);
		    return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	/**
	 * 
	 * @return
	 */
	public static Jobs getJobsList(){
		try
		{
			File file = new File("./jobs/jobs.xml");
			JAXBContext context = JAXBContext.newInstance(Jobs.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Jobs)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/*Nachschauen!*/
	/**
	 * 
	 * @param photo
	 * @return
	 */
	public static Boolean putPhotosList(Photo photo){
		try
		{
			File photosListFile = new File(photo.getOwnerRef() + "/photos/photos.xml");
		    Photos photosList;
		    if(photosListFile.createNewFile())
		    	photosList = new Photos();
		    else
		    	photosList = getPhotosList(photo.getOwnerRef());
		    
		    if(photosList != null)
		    {
		    	PhotoRef photoRef = new PhotoRef();
		    	photoRef.setUri(photo.getRef());
		    	photosList.getPhotoRef().add(photoRef);
		    }
			JAXBContext context = JAXBContext.newInstance(Photos.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(photosList, photosListFile);
		    return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	/**
	 * 
	 * @param ownerRef
	 * @return
	 */
	public static Photos getPhotosList(String ownerRef){
		try
		{
			File file = new File(ownerRef + "/photos/photos.xml");
			JAXBContext context = JAXBContext.newInstance(Photos.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Photos)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
}
