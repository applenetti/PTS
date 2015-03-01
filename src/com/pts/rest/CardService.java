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

import com.pts.business.CardBusiness;
import com.pts.exception.ApplicationException;
import com.pts.exception.CardException;
import com.pts.pojo.Card;

@Path("/card")
public class CardService {
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCard(@PathParam("id") int id) {
		Card card = null;
		try {
			card = new CardBusiness().getCard(id);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(card).build();
	}
	
	@GET
	@Path("/cards")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCards() {
		List<Card> cards = null;
		try {
			cards = new CardBusiness().getCards();
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(cards).build();
	}
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCard(@FormParam("cardTypeId") int cardTypeId, @FormParam("bankId") int bankId, @FormParam("cardNumber") String cardNumber) {
		Card card = null;
		try {
			card = new CardBusiness().createCard(cardNumber, cardTypeId, bankId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.CREATED).entity(card).build();
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCard(@FormParam("id") int cardId, @FormParam("cardNumber") String cardNumber) {
		Card card = null;
		try {
			card = new CardBusiness().updateCard(cardId, cardNumber);
		} catch (ApplicationException e) {
			if (e.getErrorMessage() == CardException.EXISTS.getErrorMessage()) {
				return Response.status(Status.CONFLICT).entity(e.getErrorMessage()).build();
			} else if (e.getErrorMessage() == CardException.NOTFOUND.getErrorMessage()) {
				return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
			}
		}
		return Response.status(Status.OK).entity(card).build();
	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCard(@FormParam("id") int cardId) {
		boolean isDeleted = false;
		try {
			isDeleted = new CardBusiness().deleteCard(cardId);
		} catch (ApplicationException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrorMessage()).build();
		}
		return Response.status(Status.OK).entity(isDeleted).build();
	}

}
