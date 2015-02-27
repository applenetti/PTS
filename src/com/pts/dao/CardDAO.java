package com.pts.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.pts.exception.ApplicationException;
import com.pts.exception.CardException;
import com.pts.exception.SystemError;
import com.pts.pojo.Bank;
import com.pts.pojo.Card;
import com.pts.pojo.CardType;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class CardDAO {
	
	public Card createCard(String cardNumber, int cardTypeId, int bankId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Card card = null;
		Bank bank = null;
		CardType cardType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Card.class);
			criteria.add(Restrictions.eq(COLUMNS.CARDNUMBER.getColumn(), cardNumber));
			List<?> cards = criteria.list();
			if (cards.isEmpty()) {
				bank = new Bank();
				bank.setId(bankId);
				cardType = new CardType();
				cardType.setId(cardTypeId);
				card = new Card();
				card.setCardNumber(cardNumber);
				card.setBank(bank);
				card.setCardType(cardType);
				session.save(card);
				transaction.commit();
			} else {
				throw new ApplicationException(CardException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return card;
	}
	
	public Card getCard(int cardId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Card card = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			card = (Card) session.get(Card.class, cardId);
			if (card == null) {
				throw new ApplicationException(CardException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return card;
	}
	
	@SuppressWarnings("unchecked")
	public List<Card> getCards() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Card> cards = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Card");
			cards = query.list();
			if (cards == null || cards.isEmpty()) {
				throw new ApplicationException(CardException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return cards;
	}
	
	public Card updateCard(int cardId, String cardName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Card card = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			card = (Card) session.get(Card.class, cardId);
			if (card == null) {
				throw new ApplicationException(CardException.NOTFOUND);
			}
			//card.setType(cardName);
			session.update(card);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return card;
	}
	
	public boolean deleteCard(int cardId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Card card = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			card = (Card) session.get(Card.class, cardId);
			if (card == null) {
				throw new ApplicationException(CardException.NOTFOUND);
			}
			session.delete(card);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return true;
	}
	
}