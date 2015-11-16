package com.pts.dao;
import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.pts.exception.ApplicationException;
import com.pts.exception.PaymentException;
import com.pts.exception.SystemError;
import com.pts.pojo.Bill;
import com.pts.pojo.Payment;
import com.pts.pojo.PaymentMode;
import com.pts.pojo.Status;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class PaymentDAO {
	
	public Payment createPayment(int billId, int statusId, int paymentModeId, Date paymentDate, double paidAmount) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;
		Status status = null;
		PaymentMode mode = null;
		Payment payment = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Payment.class);
			criteria.add(Restrictions.eq(COLUMNS.BILLID.getColumn(), billId));
			List<?> payments = criteria.list();
			if (payments.isEmpty()) {
				bill = new Bill();
				bill.setId(billId);
				
				status = new Status();
				status.setId(statusId);
				
				mode = new PaymentMode();
				mode.setId(paymentModeId);
				
				payment = new Payment();
				payment.setPaymentDate(paymentDate);
				payment.setPaidAmount(paidAmount);
				
				payment.setBill(bill);
				payment.setStatus(status);
				
				session.save(payment);
				transaction.commit();
			} else {
				throw new ApplicationException(PaymentException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return payment;
	}
	
	public Payment getPayment(int paymentId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Payment payment = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			payment = (Payment) session.get(Payment.class, paymentId);
			if (payment == null) {
				throw new ApplicationException(PaymentException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return payment;
	}
	
	@SuppressWarnings("unchecked")
	public List<Payment> getPayments() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Payment> payments = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Payment");
			payments = query.list();
			if (payments == null || payments.isEmpty()) {
				throw new ApplicationException(PaymentException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return payments;
	}
	
	@SuppressWarnings("unchecked")
	public Payment updatePayment(int id, int billId, int statusId, int paymentModeId, Date paymentDate, double paidAmount) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;
		Status status = null;
		PaymentMode mode = null;
		Payment payment = null;
		List<Bill> bills = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			payment = (Payment) session.get(Payment.class, id);
			if (payment == null) {
				throw new ApplicationException(PaymentException.NOTFOUND);
			}
			Criteria criteria = session.createCriteria(Bill.class);
			criteria.add(Restrictions.eq(COLUMNS.BILLID.getColumn(), billId));
			bills = criteria.list();
			if (!bills.isEmpty()) {
				throw new ApplicationException(PaymentException.EXISTS);
			}
			bill = new Bill();
			bill.setId(billId);
			
			status = new Status();
			status.setId(statusId);
			
			mode = new PaymentMode();
			mode.setId(paymentModeId);
			
			payment = new Payment();
			payment.setPaymentDate(paymentDate);
			payment.setPaidAmount(paidAmount);
			
			payment.setBill(bill);
			payment.setStatus(status);
			session.update(payment);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return payment;
	}
	
	public boolean deletePayment(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Payment payment = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			payment = (Payment) session.get(Payment.class, id);
			if (payment == null) {
				throw new ApplicationException(PaymentException.NOTFOUND);
			}
			session.delete(payment);
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