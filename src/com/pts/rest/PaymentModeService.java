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

import com.pts.business.PaymentModeBusiness;
import com.pts.exception.ApplicationException;
import com.pts.pojo.PaymentMode;

@Path("/paymentmode")
public class PaymentModeService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentMode(@PathParam("id") int id) {
		PaymentMode paymentMode = null;
		try {
			paymentMode = new PaymentModeBusiness().getPaymentMode(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(paymentMode).build();
	}
	
	@GET
	@Path("/paymentmodes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPaymentModes() {
		List<PaymentMode> paymentModes = null;
		try {
			paymentModes = new PaymentModeBusiness().getPaymentModes();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(paymentModes).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPaymentMode(@FormParam("mode") String paymentModeName) {
		PaymentMode paymentMode = null;
		try {
			paymentMode = new PaymentModeBusiness().createPaymentMode(paymentModeName);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(paymentMode).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePaymentMode(@FormParam("id") int paymentModeId, @FormParam("mode") String paymentModeName) {
		PaymentMode paymentMode = null;
		try {
			paymentMode = new PaymentModeBusiness().updatePaymentMode(paymentModeId, paymentModeName);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(paymentMode).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePaymentMode(@FormParam("id") int paymentModeId) {
		boolean isDeleted = false;
		try {
			isDeleted = new PaymentModeBusiness().deletePaymentMode(paymentModeId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
