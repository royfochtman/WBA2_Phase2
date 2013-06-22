package main.java.com.photobay.webservice;
import java.io.File;
import java.io.IOException;

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
import main.java.com.photobay.jaxbfiles.PhotoSells;
import main.java.com.photobay.jaxbfiles.PhotoSells.PhotoSellRef;
import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.Photographers;
import main.java.com.photobay.jaxbfiles.Photographers.PhotographerRef;
import main.java.com.photobay.jaxbfiles.Photos.PhotoRef;
import main.java.com.photobay.jaxbfiles.PressAgencies.PressAgencyRef;
import main.java.com.photobay.jaxbfiles.Photos;
import main.java.com.photobay.jaxbfiles.PressAgencies;
import main.java.com.photobay.jaxbfiles.PressAgency;
import main.java.com.photobay.util.DeleteFolder;
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
			String pathMain = "./photographers/" + id;
			String pathPhotos = pathMain + "/photos";
			String pathPhotoSells = pathMain + "/photoSells";
			File file = new File(pathMain + "/photographer.xml");
		
			if(new File("./photographers/" + id).mkdir() && file.createNewFile())
			{
//				/*creates the folder for the photos*/
//				if(new File(pathPhotos).mkdir())
//				/* creates a new XML File for the photos, but empty */
//					new File(pathPhotos + "/photos.xml").createNewFile();
//				
//				/* creates a new XML File for the PhotoSells*/
//				if(new File(pathPhotoSells).mkdir())
//					new File(pathPhotoSells + "/photoSells.xml").createNewFile();	
				new File(pathPhotoSells).mkdir();	
				new File(pathPhotos).mkdir();
				JAXBContext context = JAXBContext.newInstance(Photographer.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    
			    photographer.setID(id);
			    photographer.setRef(pathMain);
			    photographer.setPhotosRef(pathPhotos);
			    photographer.setPhotoSellsRef(pathPhotoSells);
			    
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
			String pathMain = "./pressAgencies/" + id;
			String pathPhotos = pathMain + "/photos";
			String pathJobs = pathMain + "/jobs";
			File file = new File(pathMain + "/PressAgency.xml");
			
			if(new File(pathMain).mkdir() && file.createNewFile())
			{
//				/* creates a new XML File for the photos */
//				if(new File(pathPhotos).mkdir())
//					new File(pathPhotos + "/photos.xml").createNewFile();
//				
//				/* creates a new XML File for Jobs */
//				if(new File(pathJobs).mkdir())
//					new File(pathJobs + "/jobs.xml").createNewFile();
				new File(pathJobs).mkdir();
				new File(pathPhotos).mkdir();
				JAXBContext context = JAXBContext.newInstance(PressAgency.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    pressAgency.setID(id);
			    /* Referenz zu sich selbst */
			    pressAgency.setRef(pathMain);
			    pressAgency.setJobsRef(pathJobs);
			    pressAgency.setPhotosRef(pathPhotos);
			    
			    m.marshal(pressAgency, file);
			    putPressAgenciesList(pressAgency);
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
				String pathMain = "./photoSells/" + photoSellID + "/bids/" + id ;
				File file = new File(pathMain + "/Bid.xml");
				if(new File(pathMain).mkdir() && file.createNewFile())
				{
					JAXBContext context = JAXBContext.newInstance(Bid.class);
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				    
				    bid.setID(id);
				    bid.setRef(pathMain);
				    bid.setPhotoSellRef("./photoSells/" + photoSellID);
				    bid.setPressAgencyRef(pressAgencyRef);
				    
				    m.marshal(bid, file);
				    putBidsList(bid, "./photoSells/" + photoSellID);
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
	public static Boolean postJob(Job job){
			try
			{
				int id = IdGenerator.generateID("jobs");
				String pathMain = "./jobs/" + id;
				String pathJobApplication = pathMain + "/jobApplications";
				File file = new File(pathMain + "/job.xml");
				if(new File(pathMain).mkdir() && file.createNewFile())
				{
					
					job.setID(id);
				    job.setRef(pathMain);
					
					new File(pathJobApplication).mkdir();
					JAXBContext context = JAXBContext.newInstance(Job.class);
				    Marshaller m = context.createMarshaller();
				    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				   
				    putJobsList(job);
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
	 * 
	 * @param id
	 * @return
	 */
	public static Boolean deleteJob(int id){
		File dir = new File("./jobs/" + id);
		File file = new File("./jobs/" + id + "/job.xml");
		File jobsFile = new File("./jobs/jobs.xml");
		File jobApplicationsFile = new File(dir + "/jobApplications");
		Job ownerJob = getJob(id);
		String dirString = "./jobs/" + id;
		String ownerRef = ownerJob.getPressAgencyRef();
		File ownerJobs = new File(ownerRef + "/jobs/jobs.xml");
		
		Jobs jobs = getJobsList(null);
		if(jobs != null)
		{
			//Delete JobRef in jobs/jobs.xml
			for(JobRef ref : jobs.getJobRef() ){
				if((dirString).equals(ref.getUri())){
					jobs.getJobRef().remove(ref);
					break;
				}
			}
		}
		
		try{
		
		JAXBContext context = JAXBContext.newInstance(Jobs.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    m.marshal(jobs, jobsFile);
		} catch(Exception ex)
		{
			return false;
		}
		
		//Delete JobRef in pressAgencies/{id}/jobs/jobs.xml
		jobs = getJobsList(ownerRef);
		if(jobs != null){
			for(JobRef ref : jobs.getJobRef()){
				if(dirString.equals(ref.getUri())){
					jobs.getJobRef().remove(ref);
					break;
				}
			}
		}
		
		try{
			
			JAXBContext context = JAXBContext.newInstance(Jobs.class);
		    Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    
		    
		    m.marshal(jobs, ownerJobs);
			} catch(Exception ex)
			{
				return false;
			}
		
		
		
		try{if(DeleteFolder.delete(dir))
			return true;
		else
			return false;
		}catch (IOException ex){
			return false;
		}
	}
	
	public static Boolean putJob(Job job){
		try {
			File jobFile = new File(job.getRef() + "/job.xml");
			
			JAXBContext context = JAXBContext.newInstance(Job.class);
		    Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    
		    m.marshal(job, jobFile);
		    return true;
		} catch (Exception ex) {
			return false;
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
			String pathMain = "./jobs/" + jobID + "/jobApplications/" + id;
			File file = new File(pathMain + "/jobApplication.xml");
			if(new File(pathMain).mkdir() && file.createNewFile())
			{
				JAXBContext context = JAXBContext.newInstance(JobApplication.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    
			    jobApplication.setID(id);
			    jobApplication.setRef(pathMain);
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
	public static Boolean postPhotoSell(PhotoSell photoSell){
		try
		{
			int id = IdGenerator.generateID("photoSells");
			String pathMain = "./photoSells/" + id;
			String pathBids = pathMain + "/bids";
			File file = new File(pathMain + "/photoSell.xml");
			if(new File(pathMain).mkdir() && file.createNewFile())
			{
				new File(pathBids).mkdir();
				JAXBContext context = JAXBContext.newInstance(PhotoSell.class);
			    Marshaller m = context.createMarshaller();
			    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			    
			    photoSell.setID(id);
			    photoSell.setRef(pathMain);
			    photoSell.setBidsRef(pathBids);
			    photoSell.setPhotographerRef(photoSell.getPhotographerRef());
			    putPhotoSellsList(photoSell);
			    
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
		    {
		    	photographersList = new Photographers();
		    	photographersList.setRef("./photographers");
		    }
		    else
		    	photographersList = getPhotographersList();
		    
		    if(photographersList != null)
		    {
		    	PhotographerRef photographerRef = new PhotographerRef();
		    	photographerRef.setFirstName(photographer.getFirstname());
		    	photographerRef.setLastName(photographer.getLastname());
		    	photographerRef.setUsername(photographer.getGeneralPersonalData().getUsername());
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
		    {
		    	pressAgenciesList = new PressAgencies();
		    	pressAgenciesList.setRef("./pressAgencies");
		    }
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
		    {
		    	bidsList = new Bids();
		    	bidsList.setRef(photoSellRef + "/bids");
		    }
		    else
		    	bidsList = getBidsList(photoSellRef);
		    
		    if(bidsList != null)
		    {
		    	BidRef bidRef = new BidRef();
		    	bidRef.setValue(bid.getBidValue());
		    	bidRef.setUri(bid.getRef());
		    	bidRef.setPressAgencyRef(bid.getPressAgencyRef());
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
		    {
		    	jobsList = new Jobs();
		    	jobsList.setRef("./jobs");
		    }
		    else
		    	jobsList = getJobsList(null);
		    
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
		    
		    if(putJobsPressAgencyList(job))
		    	return true;
		    else
		    	return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static Boolean putJobsPressAgencyList(Job job){
		try
		{
			File jobsListFile = new File(job.getPressAgencyRef() + "/jobs/jobs.xml");
		    Jobs jobsList;
		    if(jobsListFile.createNewFile())
		    {
		    	jobsList = new Jobs();
		    	jobsList.setRef(job.getPressAgencyRef() + "/jobs/jobs.xml");
		    }
		    else
		    	jobsList = getJobsList(job.getPressAgencyRef());
		    
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
	/* hinzugefügt am: 18.06.2013, 16:16 Uhr. */
	public static Jobs getJobsList(String ownerRef){
		File file;
		try
		{
			if(ownerRef == null || ownerRef.isEmpty())
//				file = new File(ownerRef +"/jobs/jobs.xml");
				file = new File("./jobs/jobs.xml");
			else
//				file = new File("./jobs/jobs.xml");
				file = new File(ownerRef + "/jobs/jobs.xml");
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
		    {
		    	photosList = new Photos();
		    	photosList.setRef(photo.getOwnerRef() + "/photos");
		    }
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
	
	/**
	 * Read the List of all PhotoSells from all Photographers and send it back
	 * @param photoSell
	 * @return PhotoSellList
	 */
	public static Boolean putPhotoSellsList(PhotoSell photoSell){
		try
		{
			File photoSellsListFile = new File("./photoSells/photoSells.xml");
		    PhotoSells photoSellsList;
		    if(photoSellsListFile.createNewFile())
		    {
		    	photoSellsList = new PhotoSells();
		    	photoSellsList.setRef("./photoSells");
		    }
		    else
		    	photoSellsList = getPhotoSellsList(null);
		    
		    if(photoSellsList != null)
		    {
		    	PhotoSellRef photoSellRef = new PhotoSellRef();
		    	photoSellRef.setPhotoSellName(photoSell.getName());
		    	photoSellRef.setUri(photoSell.getRef());
		    	photoSellsList.getPhotoSellRef().add(photoSellRef);
		    }
			JAXBContext context = JAXBContext.newInstance(PhotoSells.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(photoSellsList, photoSellsListFile);
		    
		    if(putPhotoSellsPhotographerList(photoSell))
		    	return true;
		    else
		    	return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/**
	 * Read the List of all PhotoSells from one specific Photographer, which is identified by the
	 * photographerRef-Attribute from the photoSell-Object, and send it back
	 * @param photoSell
	 * @return
	 */
	public static Boolean putPhotoSellsPhotographerList(PhotoSell photoSell)
	{
		try
		{
			File photoSellsListFile = new File(photoSell.getPhotographerRef() + "/photoSells/photoSells.xml");
		    PhotoSells photoSellsList;
		    if(photoSellsListFile.createNewFile())
		    {
		    	photoSellsList = new PhotoSells();
		    	photoSellsList.setRef(photoSell.getPhotographerRef() + "/photoSells");
		    }
		    else
		    	photoSellsList = getPhotoSellsList(photoSell.getPhotographerRef());
		    
		    if(photoSellsList != null)
		    {
		    	PhotoSellRef photoSellRef = new PhotoSellRef();
		    	photoSellRef.setPhotoSellName(photoSell.getName());
		    	photoSellRef.setUri(photoSell.getRef());
		    	photoSellsList.getPhotoSellRef().add(photoSellRef);
		    }
			JAXBContext context = JAXBContext.newInstance(PhotoSells.class);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(photoSellsList, photoSellsListFile);
		    return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	/**
	 * Gets the existing PhotoSellList. If ownerRef is not Null and not empty the method returns the specific 
	 * List from the Photographer given in the ownerRef. Otherwise the method returns the List of all PhotoSells from
	 * all registered Photographers.
	 * @param ownerRef
	 * @return
	 */
	public static PhotoSells getPhotoSellsList(String ownerRef) {
		File file;
		try
		{
			if(ownerRef == null || ownerRef.isEmpty())
				file = new File("./photoSells/photoSells.xml");
			else
				file = new File(ownerRef + "/photoSells/photoSells.xml");
			JAXBContext context = JAXBContext.newInstance(PhotoSells.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (PhotoSells)unmarshaller.unmarshal(file);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
}
