package main.java.com.photobay.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.com.photobay.jaxbfiles.JobApplication;

@Path("/jobs/{id}/jobApplications")
public class JobApplicationsService {
	
	@POST
	@Consumes({"application/xml"})
	public Response postJobApplication(JobApplication jobApplication, @PathParam("id") int jobId, String photographerRef)
	{
		if(PhotoBayRessourceManager.postJobApplication(jobApplication, jobId, photographerRef))
		{
			return Response.ok().build();
		}
		else return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/{jobApplicationID}")
	@Produces({"application/xml"})
	public Response getJobApplication(@PathParam("id") int jobId, @PathParam("jobApplicationID") int jobApplicationId)
	{
		JobApplication jobApplication = PhotoBayRessourceManager.getJobApplication(jobId, jobApplicationId);
		if(jobApplication == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(jobApplication).build();
	}
}
