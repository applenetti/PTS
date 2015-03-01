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
import com.pts.exception.CardTypeException;
import com.pts.exception.SystemError;
import com.pts.pojo.CardType;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class CardTypeDAO {
	
	public CardType createCardType(String cardTypeDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		CardType cardType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(CardType.class);
			criteria.add(Restrictions.eq(COLUMNS.CARDTYPE.getColumn(), cardTypeDesc));
			List<?> cardTypes = criteria.list();
			if (cardTypes.isEmpty()) {
				cardType = new CardType();
				cardType.setCardType(cardTypeDesc);
				session.save(cardType);
				transaction.commit();
			} else {
				throw new ApplicationException(CardTypeException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return cardType;
	}
	
	public CardType getCardType(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		CardType cardType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			cardType = (CardType) session.get(CardType.class, id);
			if (cardType == null) {
				throw new ApplicationException(CardTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return cardType;
	}
	
	@SuppressWarnings("unchecked")
	public List<CardType> getCardTypes() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<CardType> cardTypes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from CardType");
			cardTypes = query.list();
			if (cardTypes == null || cardTypes.isEmpty()) {
				throw new ApplicationException(CardTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return cardTypes;
	}
	
	@SuppressWarnings("unchecked")
	public CardType updateCardType(int id, String cardTypeDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		CardType cardType = null;
		List<CardType> cardTypes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			cardType = (CardType) session.get(CardType.class, id);
			if (cardType == null) {
				throw new ApplicationException(CardTypeException.NOTFOUND);
			}
			Criteria criteria = session.createCriteria(CardType.class);
			criteria.add(Restrictions.eq(COLUMNS.CARDTYPE.getColumn(), cardTypeDesc));
			cardTypes = criteria.list();
			if (!cardTypes.isEmpty()) {
				throw new ApplicationException(CardTypeException.EXISTS);
			}
			cardType.setCardType(cardTypeDesc);
			session.update(cardType);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return cardType;
	}
	
	public boolean deleteCardType(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		CardType cardType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			cardType = (CardType) session.get(CardType.class, id);
			if (cardType == null) {
				throw new ApplicationException(CardTypeException.NOTFOUND);
			}
			session.delete(cardType);
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