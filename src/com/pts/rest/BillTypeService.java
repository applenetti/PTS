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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pts.business.BillTypeBusiness;
import com.pts.exception.ApplicationException;
import com.pts.exception.BillTypeException;
import com.pts.pojo.BillType;

@Path("/billtype")
public class BillTypeService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillType(@PathParam("id") int id) {
		BillType billType = null;
		try {
			billType = new BillTypeBusiness().getBillType(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billType).build();
	}
	
	@GET
	@Path("/billtypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillTypes() {
		List<BillType> billTypes = null;
		try {
			billTypes = new BillTypeBusiness().getBillTypes();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billTypes).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBillType(@FormParam("billType") String billTypeDesc) {
		BillType billType = null;
		try {
			billType = new BillTypeBusiness().createBillType(billTypeDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(billType).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBillType(@FormParam("id") int id, @FormParam("billType") String billTypeDesc) {
		BillType billType = null;
		try {
			billType = new BillTypeBusiness().updateBillType(id, billTypeDesc);
		} catch (ApplicationException e) {
			if (e.getErrorMessage() == BillTypeException.EXISTS.getErrorMessage()) {
				return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
			} else if (e.getErrorMessage() == BillTypeException.NOTFOUND.getErrorMessage()) {
				return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
			}
		}
		return Response.status(Status.OK).entity(billType).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBillType(@FormParam("id") int id) {
		boolean isDeleted = false;
		try {
			isDeleted = new BillTypeBusiness().deleteBillType(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
