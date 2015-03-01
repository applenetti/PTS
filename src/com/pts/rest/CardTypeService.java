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

import com.pts.business.CardTypeBusiness;
import com.pts.exception.ApplicationException;
import com.pts.exception.CardTypeException;
import com.pts.pojo.CardType;

@Path("/cardtype")
public class CardTypeService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCardType(@PathParam("id") int id) {
		CardType cardType = null;
		try {
			cardType = new CardTypeBusiness().getCardType(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(cardType).build();
	}
	
	@GET
	@Path("/cardtypes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCardTypes() {
		List<CardType> cardTypes = null;
		try {
			cardTypes = new CardTypeBusiness().getCardTypes();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(cardTypes).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCardType(@FormParam("cardType") String cardTypeDesc) {
		CardType cardType = null;
		try {
			cardType = new CardTypeBusiness().createCardType(cardTypeDesc);
		} catch (ApplicationException e) {
			return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(cardType).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCardType(@FormParam("id") int id, @FormParam("cardType") String cardTypeDesc) {
		CardType cardType = null;
		try {
			cardType = new CardTypeBusiness().updateCardType(id, cardTypeDesc);
		} catch (ApplicationException e) {
			if (e.getErrorMessage() == CardTypeException.EXISTS.getErrorMessage()) {
				return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
			} else if (e.getErrorMessage() == CardTypeException.NOTFOUND.getErrorMessage()) {
				return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
			}
		}
		return Response.status(Status.OK).entity(cardType).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCardType(@FormParam("id") int cardTypeId) {
		boolean isDeleted = false;
		try {
			isDeleted = new CardTypeBusiness().deleteCardType(cardTypeId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
