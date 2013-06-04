package main.java.com.photobay.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import main.java.com.photobay.jaxbfiles.*;

import main.java.com.photobay.webservice.PhotoBayRessourceManager;

@Path("/host")
public class PhotoBayService {
	
	@GET
	@Path("/photographers/{id}")
	@Produces({"application/xml"})
	public Response getPhotographer(@PathParam("id") int id)
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
	@Path("/photographers/{id}")
	public Response deletePhotographer(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotographer(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	
	@GET
	@Path("/pressAgencies/{id}")
	@Produces({"application/xml"})
	public Response getPressAgency(@PathParam("id") int id)
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
	@Path("/pressAgencies/{id}")
	public Response deletePressAgency(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePressAgency(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	@GET
	@Path("/jobs/{id}")
	@Produces({"application/xml"})
	public Response getJob(@PathParam("id") int id)
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
	@Path("/photoSells/{id}")
	@Produces({"application/xml"})
	public Response getPhotoSell(@PathParam("id") int id)
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
	@Path("/photoSells/{id}")
	public Response deletePhotoSell(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotoSell(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
}
