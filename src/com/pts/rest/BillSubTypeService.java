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

import com.pts.business.BillSubTypeBusiness;
import com.pts.exception.ApplicationException;
import com.pts.pojo.BillSubType;

@Path("/billsubtype")
public class BillSubTypeService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillSubType(@PathParam("id") int id) {
		BillSubType billSubType = null;
		try {
			billSubType = new BillSubTypeBusiness().getBillSubType(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billSubType).build();
	}
	
	@GET
	@Path("/billsubtypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillSubTypes() {
		List<BillSubType> billSubTypes = null;
		try {
			billSubTypes = new BillSubTypeBusiness().getBillSubTypes();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billSubTypes).build();
	}
	
	@GET
	@Path("/billsubtypes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillSubTypesOfType(@PathParam("id") int typeId) {
		List<BillSubType> billSubTypes = null;
		try {
			billSubTypes = new BillSubTypeBusiness().getBillSubTypes(typeId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billSubTypes).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBillSubType(@FormParam("type") int typeId, @FormParam("subType") String billSubTypeDesc) {
		BillSubType billSubType = null;
		try {
			billSubType = new BillSubTypeBusiness().createBillSubType(typeId, billSubTypeDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(billSubType).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBillSubType(@FormParam("id") int id, @FormParam("typeId") int billTypeId, @FormParam("subType") String billSubTypeDesc) {
		BillSubType billSubType = null;
		try {
			billSubType = new BillSubTypeBusiness().updateBillSubType(id, billTypeId, billSubTypeDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(billSubType).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBillSubType(@FormParam("id") int id) {
		boolean isDeleted = false;
		try {
			isDeleted = new BillSubTypeBusiness().deleteBillSubType(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
