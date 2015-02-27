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

import com.pts.business.StatusBusiness;
import com.pts.exception.ApplicationException;

@Path("/status")
public class StatusService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatus(@PathParam("id") int id) {
		com.pts.pojo.Status status = null;
		try {
			status = new StatusBusiness().getStatus(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(status).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatus(@QueryParam("status") String statusDesc) {
		com.pts.pojo.Status status = null;
		try {
			status = new StatusBusiness().getStatus(statusDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(status).build();
	}
	
	@GET
	@Path("/statuses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatuss() {
		List<com.pts.pojo.Status> statuss = null;
		try {
			statuss = new StatusBusiness().getStatuss();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(statuss).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStatus(@FormParam("status") String statusDesc) {
		com.pts.pojo.Status status = null;
		try {
			status = new StatusBusiness().createStatus(statusDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(status).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStatus(@FormParam("id") int id, @FormParam("status") String statusDesc) {
		com.pts.pojo.Status status = null;
		try {
			status = new StatusBusiness().updateStatus(id, statusDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(status).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteStatus(@FormParam("id") int id) {
		boolean isDeleted = false;
		try {
			isDeleted = new StatusBusiness().deleteStatus(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
