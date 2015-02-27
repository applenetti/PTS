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

import com.pts.business.AccountBusiness;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Account;

@Path("/account")
public class AccountService {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("id") int id) {
		Account account = null;
		try {
			account = new AccountBusiness().getAccount(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(account).build();
	}

	@GET
	@Path("/accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts() {
		List<Account> accounts = null;
		try {
			accounts = new AccountBusiness().getAccounts();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(accounts).build();
	}
	
	@GET
	@Path("/accounts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountsById(@PathParam("id") int accountId) {
		List<Account> accounts = null;
		try {
			accounts = new AccountBusiness().getAccountsById(accountId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(accounts).build();
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(@FormParam("accountId") String accountId,
			@FormParam("accountNumber") String accountNumber,
			@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("billerId") int billerId) {
		Account account = null;
		try {
			account = new AccountBusiness().createAccount(accountId,
					accountNumber, username, password, billerId);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(account).build();
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@FormParam("id") int id, @FormParam("accountId") String accountId,
			@FormParam("accountNumber") String accountNumber,
			@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("bId") int billerId, @FormParam("name") String billerName) {
		Account account = null;
		try {
			account = new AccountBusiness().updateAccount(id, accountId,
					accountNumber, username, password, billerId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(account).build();
	}

	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@FormParam("id") int accountId) {
		boolean isDeleted = false;
		try {
			isDeleted = new AccountBusiness().deleteAccount(accountId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND)
					.entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
