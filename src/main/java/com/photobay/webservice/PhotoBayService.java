package main.java.com.photobay.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import main.java.com.photobay.jaxbfiles.*;

import main.java.com.photobay.webservice.PhotoBayRessourceManager;

@Path("/host")
public class PhotoBayService {
	
	@GET
	@Path("/photographer/{id}")
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
	@Path("/photographer")
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
	@Path("/photographer/{id}")
	public Response deletePhotographer(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotographer(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	
	@GET
	@Path("/pressAgency/{id}")
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
	@Path("/pressAgency")
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
	@Path("/pressAgency/{id}")
	public Response deletePressAgency(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePressAgency(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
}
