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
import com.pts.exception.PaymentModeException;
import com.pts.exception.SystemError;
import com.pts.pojo.PaymentMode;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class PaymentModeDAO {
	
	public PaymentMode createPaymentMode(String mode) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		PaymentMode paymentMode = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(PaymentMode.class);
			criteria.add(Restrictions.eq(COLUMNS.MODE.getColumn(), mode));
			List<?> paymentModes = criteria.list();
			if (paymentModes.isEmpty()) {
				paymentMode = new PaymentMode();
				paymentMode.setMode(mode);
				session.save(paymentMode);
				transaction.commit();
			} else {
				throw new ApplicationException(PaymentModeException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return paymentMode;
	}
	
	public PaymentMode getPaymentMode(int paymentModeId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		PaymentMode paymentMode = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			paymentMode = (PaymentMode) session.get(PaymentMode.class, paymentModeId);
			if (paymentMode == null) {
				throw new ApplicationException(PaymentModeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return paymentMode;
	}
	
	@SuppressWarnings("unchecked")
	public List<PaymentMode> getPaymentModes() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<PaymentMode> paymentModes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from PaymentMode");
			paymentModes = query.list();
			if (paymentModes == null || paymentModes.isEmpty()) {
				throw new ApplicationException(PaymentModeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return paymentModes;
	}
	
	public PaymentMode updatePaymentMode(int paymentModeId, String paymentModeName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		PaymentMode paymentMode = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			paymentMode = (PaymentMode) session.get(PaymentMode.class, paymentModeId);
			if (paymentMode == null) {
				throw new ApplicationException(PaymentModeException.NOTFOUND);
			}
			paymentMode.setMode(paymentModeName);
			session.update(paymentMode);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return paymentMode;
	}
	
	public boolean deletePaymentMode(int paymentModeId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		PaymentMode paymentMode = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			paymentMode = (PaymentMode) session.get(PaymentMode.class, paymentModeId);
			if (paymentMode == null) {
				throw new ApplicationException(PaymentModeException.NOTFOUND);
			}
			session.delete(paymentMode);
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