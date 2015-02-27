package com.pts.business;

import java.util.List;

import com.pts.dao.CardTypeDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.CardType;

public class CardTypeBusiness {

	public CardType getCardType(int id) throws ApplicationException {
		CardType cardType = null;
		try {
			cardType = new CardTypeDAO().getCardType(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return cardType;
	}
	
	public List<CardType> getCardTypes() throws ApplicationException {
		List<CardType> cardTypes = null;
		try {
			cardTypes = new CardTypeDAO().getCardTypes();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return cardTypes;
	}

	public CardType createCardType(String cardTypeDesc) throws ApplicationException {
		CardType cardType = null;
		try {
			cardType = new CardTypeDAO().createCardType(cardTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return cardType;
	}

	public CardType updateCardType(int id, String cardTypeDesc) throws ApplicationException {
		CardType cardType = null;
		try {
			cardType = new CardTypeDAO().updateCardType(id, cardTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return cardType;
	}

	public boolean deleteCardType(int id) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new CardTypeDAO().deleteCardType(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
