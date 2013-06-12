package main.java.com.photobay.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import main.java.com.photobay.jaxbfiles.Bid;

@Path("/photoSells/{id}/bids")
public class BidsService {
	
	@POST
	//@Path("/photoSells/{id}/bids")
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
	@Path("/{bidID}")
	@Produces({"application/xml"})
	public Response getBid(@PathParam("id") int photoSellId, @PathParam("bidID") int bidId)
	{
		Bid bid = PhotoBayRessourceManager.getBid(photoSellId, bidId);
		if(bid == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(bid).build();
	}
}
