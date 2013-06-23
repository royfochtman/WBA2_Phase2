package main.java.com.photobay.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.com.photobay.jaxbfiles.PhotoSell;
import main.java.com.photobay.jaxbfiles.PhotoSells;

@Path("/photoSells")
public class PhotoSellsService {
	
	@GET
	@Path("/{id}")
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
	@Consumes({"application/xml"})
	public Response postPhotoSell(PhotoSell photoSell)
	{
		if(PhotoBayRessourceManager.postPhotoSell(photoSell))
		{
			return Response.ok(photoSell).build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletePhotoSell(@PathParam("id") int id)
	{
		if(PhotoBayRessourceManager.deletePhotoSell(id))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response getPhotoSellsList()
	{
		PhotoSells photoSells = PhotoBayRessourceManager.getPhotoSellsList(null);
		if(photoSells == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(photoSells).build();
	}
	
	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPhotoSellsList(@QueryParam("owner") String ownerRef)
	{
		PhotoSells photoSells = PhotoBayRessourceManager.getPhotoSellsList(ownerRef);
		if(photoSells == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(photoSells).build();
	}

}
