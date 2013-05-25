package main.java.com.photobay.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import main.java.com.photobay.jaxbfiles.*;

import main.java.com.photobay.webservice.PhotoBayRessourceManager;

@Path("/host")
public class PhotoBayService {
	
	@GET
	@Path("{photographer}")
	@Produces({"application/xml"})
	public Response getPhotographer(@PathParam("photographer") int id)
	{
		Photographer photographer = PhotoBayRessourceManager.getPhotographer(id);
		if(photographer == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(photographer).build();
	}


	@PUT
	@Path("{photographer}")
	@Consumes({"application/xml"})
	public Response putPhotographer(Photographer photographer)
	{
		if(PhotoBayRessourceManager.putPhotographer(photographer))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("{photographer}")
	public Response deletePhotographer(@PathParam("photographer") int id)
	{
		if(PhotoBayRessourceManager.deletePhotographer(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	
	@GET
	@Path("{pressAgency}")
	@Produces({"application/xml"})
	public Response getPressAgency(@PathParam("pressAgency") int id)
	{
		PressAgency pressAgency = PhotoBayRessourceManager.getPressAgency(id);
		if(pressAgency == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(pressAgency).build();
	}
	
	@PUT
	@Path("{pressAgency}")
	@Consumes({"application/xml"})
	public Response putPressAgency(PressAgency pressAgency)
	{
		if(PhotoBayRessourceManager.putPressAgency(pressAgency))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DELETE
	@Path("{pressAgency}")
	public Response deletePressAgency(@PathParam("pressAgency") int id)
	{
		if(PhotoBayRessourceManager.deletePressAgency(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
}
