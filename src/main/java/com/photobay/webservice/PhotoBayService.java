package main.java.com.photobay.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import main.java.com.photobay.jaxbfiles.*;

import main.java.com.photobay.webservice.PhotoBayRessourceManager;

@Path("/host")
public class PhotoBayService {
	
	@GET
	@Path("{photographer}")
	public Response getPhotographer(@PathParam("photographer") int id)
	{
		Photographer photographer = PhotoBayRessourceManager.getPhotographer(id);
		return Response.ok(photographer).build();
	}
	
	@GET
	@Path("{pressAgency}")
	public Response getPressAgency(@PathParam("pressAgency") int id)
	{
		PressAgency pressAgency = PhotoBayRessourceManager.getPressAgency(id);
		return Response.ok(pressAgency).build();
	}
	
}
