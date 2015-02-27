package com.pts.rest;

import java.util.Date;
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

import com.pts.business.BillBusiness;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Bill;

@Path("/bill")
public class BillService {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBill(@PathParam("id") int id) {
		Bill bill = null;
		try {
			bill = new BillBusiness().getBill(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(bill).build();
	}

	@GET
	@Path("/bills")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBills() {
		List<Bill> bills = null;
		try {
			bills = new BillBusiness().getBills();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(bills).build();
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBill(@FormParam("billTypeId") int billTypeId, @FormParam("billSubTypeId") int billSubTypeId, @FormParam("billerId") int billerId, @FormParam("accountId") int accountId,
			@FormParam("billNumber") String billNumber, @FormParam("billDate") Date billDate, @FormParam("billDueDate") Date billDueDate, @FormParam("billAmount") double billAmount) {
		Bill bill = null;
		try {
			bill = new BillBusiness().createBill(billTypeId, billSubTypeId, billerId, accountId, billNumber, billDate, billDueDate, billAmount);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(bill).build();
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBill(@FormParam("id") int id, @FormParam("billId") String billId,
			@FormParam("billNumber") String billNumber,
			@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("bId") int billerId, @FormParam("name") String billerName) {
		Bill bill = null;
		try {
			bill = new BillBusiness().updateBill(id, billId,
					billNumber, username, password, billerId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(bill).build();
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBill(@FormParam("id") int billId) {
		boolean isDeleted = false;
		try {
			isDeleted = new BillBusiness().deleteBill(billId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
