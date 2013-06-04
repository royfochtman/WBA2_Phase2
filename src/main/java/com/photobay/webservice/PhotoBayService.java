package main.java.com.photobay.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import main.java.com.photobay.jaxbfiles.*;

import main.java.com.photobay.webservice.PhotoBayRessourceManager;

@Path("/host")
public class PhotoBayService {
	
	@GET
	@Path("/photographers")
	@Produces({"application/xml"})
	public Response getPhotographer(@QueryParam("id") int id)
	{
		Photographer photographer = PhotoBayRessourceManager.getPhotographer(id);
		if(photographer == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(photographer).build();
	}


	@POST
	@Path("/photographers")
	@Consumes({"application/xml"})
	public Response postPhotographer(Photographer photographer)
	{
		if(PhotoBayRessourceManager.postPhotographer(photographer))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("/photographers")
	public Response deletePhotographer(@QueryParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotographer(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	
	@GET
	@Path("/pressAgencies")
	@Produces({"application/xml"})
	public Response getPressAgency(@QueryParam("id") int id)
	{
		PressAgency pressAgency = PhotoBayRessourceManager.getPressAgency(id);
		if(pressAgency == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(pressAgency).build();
	}
	
	@POST
	@Path("/pressAgencies")
	@Consumes({"application/xml"})
	public Response postPressAgency(PressAgency pressAgency)
	{
		if(PhotoBayRessourceManager.postPressAgency(pressAgency))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DELETE
	@Path("/pressAgencies")
	public Response deletePressAgency(@QueryParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePressAgency(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	@GET
	@Path("/jobs")
	@Produces({"application/xml"})
	public Response getJob(@QueryParam("id") int id)
	{
		Job job = PhotoBayRessourceManager.getJob(id);
		if(job == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(job).build();
	}


	@POST
	@Path("/jobs")
	@Consumes({"application/xml"})
	public Response postJob(Job job)
	{
		if(PhotoBayRessourceManager.postJob(job))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/photoSells")
	@Produces({"application/xml"})
	public Response getPhotoSell(@QueryParam("id") int id)
	{
		PhotoSell photoSell = PhotoBayRessourceManager.getPhotoSell(id);
		if(photoSell == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(photoSell).build();
	}


	@POST
	@Path("/photoSells")
	@Consumes({"application/xml"})
	public Response postPhotoSell(PhotoSell photoSell)
	{
		if(PhotoBayRessourceManager.postPhotoSell(photoSell))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("/photoSells")
	public Response deletePhotoSell(@QueryParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotoSell(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	@POST
	@Path("/photoSells/{id}/bids")
	@Consumes({"application/xml"})
	public Response postBid(Bid bid, @PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.postBid(bid, id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/photoSells/{id}/bids")
	@Produces({"application/xml"})
	public Response getBid(@PathParam("id") int photoSellId, @QueryParam("id") int bidId)
	{
		Bid bid = PhotoBayRessourceManager.getBid(photoSellId, bidId);
		if(bid == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(bid).build();
	}
	
	@POST
	@Path("/jobs/{id}/jobApplications")
	@Consumes({"application/xml"})
	public Response postJobApplication(JobApplication jobApplication, @PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.postJobApplication(jobApplication, id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/jobs/{id}/jobApplications")
	@Produces({"application/xml"})
	public Response getJobApplication(@PathParam("id") int jobId, @QueryParam("id") int jobApplicationId)
	{
		JobApplication jobApplication = PhotoBayRessourceManager.getJobApplication(jobId, jobApplicationId);
		if(jobApplication == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(jobApplication).build();
	}
}
