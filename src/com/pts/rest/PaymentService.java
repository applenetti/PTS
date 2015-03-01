package com.pts.rest;


import java.sql.Date;
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

import com.pts.business.PaymentBusiness;
import com.pts.exception.ApplicationException;
import com.pts.exception.PaymentException;
import com.pts.pojo.Payment;

@Path("/payment")
public class PaymentService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPayment(@PathParam("id") int id) {
		Payment paymentMode = null;
		try {
			paymentMode = new PaymentBusiness().getPayment(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(paymentMode).build();
	}
	
	@GET
	@Path("/payments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPayments() {
		List<Payment> paymentModes = null;
		try {
			paymentModes = new PaymentBusiness().getPayments();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(paymentModes).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPayment(@FormParam("billId") int billId, @FormParam("statusId") int statusId, @FormParam("paymentModeId") int paymentModeId, @FormParam("paymentDate") Date paymentDate, @FormParam("paidAmount") double paidAmount) {
		Payment paymentMode = null;
		try {
			paymentMode = new PaymentBusiness().createPayment(billId, statusId, paymentModeId, paymentDate, paidAmount);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(paymentMode).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePayment(@FormParam("id") int id, @FormParam("billId") int billId, @FormParam("statusId") int statusId, @FormParam("paymentModeId") int paymentModeId, @FormParam("paymentDate") Date paymentDate, @FormParam("paidAmount") double paidAmount) {
		Payment paymentMode = null;
		try {
			paymentMode = new PaymentBusiness().updatePayment(id, billId, statusId, paymentModeId, paymentDate, paidAmount);
		} catch (ApplicationException e) {
			if (e.getErrorMessage() == PaymentException.EXISTS.getErrorMessage()) {
				return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
			} else if (e.getErrorMessage() == PaymentException.NOTFOUND.getErrorMessage()) {
				return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
			}
		}
		return Response.status(Status.OK).entity(paymentMode).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePayment(@FormParam("id") int paymentModeId) {
		boolean isDeleted = false;
		try {
			isDeleted = new PaymentBusiness().deletePayment(paymentModeId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
