package com.pts.rest;


import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pts.business.BillerBusiness;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Biller;

@Path("/biller")
public class BillerService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBiller(@PathParam("id") int id) {
		Biller biller = null;
		try {
			biller = new BillerBusiness().getBiller(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(biller).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBiller(@QueryParam("name") String billerName) {
		Biller biller = null;
		try {
			biller = new BillerBusiness().getBiller(billerName);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(biller).build();
	}
	
	@GET
	@Path("/billers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillers() {
		List<Biller> billers = null;
		try {
			billers = new BillerBusiness().getBillers();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billers).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBiller(@FormParam("name") String billerName) {
		Biller biller = null;
		try {
			biller = new BillerBusiness().createBiller(billerName);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(biller).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBiller(@FormParam("id") int id, @FormParam("name") String billerName) {
		Biller biller = null;
		try {
			biller = new BillerBusiness().updateBiller(id, billerName);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(biller).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBiller(@FormParam("id") int id) {
		boolean isDeleted = false;
		try {
			isDeleted = new BillerBusiness().deleteBiller(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
