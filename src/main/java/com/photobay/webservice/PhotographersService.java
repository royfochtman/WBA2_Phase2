package main.java.com.photobay.webservice;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


import main.java.com.photobay.jaxbfiles.Photographer;
import main.java.com.photobay.jaxbfiles.Photographers;

@Path("/photographers")
public class PhotographersService {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
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
	@Consumes(MediaType.APPLICATION_XML)
	public Response postPhotographer(Photographer photographer)
	{
		if(PhotoBayRessourceManager.postPhotographer(photographer))
		{
				return Response.ok(photographer).build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deletePhotographer(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotographer(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getPhotographersList()
	{
		Photographers photographersList = PhotoBayRessourceManager.getPhotographersList();
		if(photographersList == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(photographersList).build();
	}
}
