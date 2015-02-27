package com.pts.business;

import java.util.List;

import com.pts.dao.CardDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Card;

public class CardBusiness {

	public Card getCard(int id) throws ApplicationException {
		Card card = null;
		try {
			card = new CardDAO().getCard(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return card;
	}
	
	public List<Card> getCards() throws ApplicationException {
		List<Card> cards = null;
		try {
			cards = new CardDAO().getCards();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return cards;
	}

	public Card createCard(String cardNumber, int cardTypeId, int bankId) throws ApplicationException {
		Card card = null;
		try {
			card = new CardDAO().createCard(cardNumber, cardTypeId, bankId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return card;
	}

	public Card updateCard(int cardId, String cardNumber) throws ApplicationException {
		Card card = null;
		try {
			card = new CardDAO().updateCard(cardId, cardNumber);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return card;
	}

	public boolean deleteCard(int cardId) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new CardDAO().deleteCard(cardId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
