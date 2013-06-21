package main.java.com.photobay.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.com.photobay.jaxbfiles.Job;

@Path("/jobs")
public class JobsService {

	@GET
	@Path("/{id}")
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
	//@Path("/jobs")
	@Consumes({"application/xml"})
	public Response postJob(Job job)
	{
		if(PhotoBayRessourceManager.postJob(job))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
}
