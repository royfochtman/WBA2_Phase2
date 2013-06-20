package main.java.com.photobay.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.com.photobay.jaxbfiles.PressAgency;

@Path("/pressAgencies")
public class PressAgenciesService {
	
	@GET
	@Path("/{id}")
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
	//@Path("/pressAgencies")
	@Consumes({"application/xml"})
	public Response postPressAgency(PressAgency pressAgency)
	{
		if(PhotoBayRessourceManager.postPressAgency(pressAgency))
		{
			return Response.ok(pressAgency).build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePressAgency(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePressAgency(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
}
