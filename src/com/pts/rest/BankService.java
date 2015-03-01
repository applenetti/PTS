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

import com.pts.business.BankBusiness;
import com.pts.exception.ApplicationException;
import com.pts.exception.BankException;
import com.pts.pojo.Bank;

@Path("/bank")
public class BankService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBank(@PathParam("id") int id) {
		Bank bank = null;
		try {
			bank = new BankBusiness().getBank(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(bank).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBank(@QueryParam("name") String bankName) {
		Bank bank = null;
		try {
			bank = new BankBusiness().getBank(bankName);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(bank).build();
	}
	
	@GET
	@Path("/banks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBanks() {
		List<Bank> banks = null;
		try {
			banks = new BankBusiness().getBanks();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(banks).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBank(@FormParam("name") String bankName) {
		Bank bank = null;
		try {
			bank = new BankBusiness().createBank(bankName);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(bank).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBank(@FormParam("id") int id, @FormParam("name") String bankName) {
		Bank bank = null;
		try {
			bank = new BankBusiness().updateBank(id, bankName);
		} catch (ApplicationException e) {
			if (e.getErrorMessage() == BankException.EXISTS.getErrorMessage()) {
				return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
			} else if (e.getErrorMessage() == BankException.NOTFOUND.getErrorMessage()) {
				return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
			}
		}
		return Response.status(Status.OK).entity(bank).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBank(@FormParam("bankId") int bankId) {
		boolean isDeleted = false;
		try {
			isDeleted = new BankBusiness().deleteBank(bankId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
