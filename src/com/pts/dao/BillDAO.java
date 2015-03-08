package com.pts.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.pts.exception.ApplicationException;
import com.pts.exception.BillException;
import com.pts.exception.SystemError;
import com.pts.pojo.Account;
import com.pts.pojo.Bank;
import com.pts.pojo.Bill;
import com.pts.pojo.Biller;
import com.pts.pojo.Status;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class BillDAO {

	@SuppressWarnings("unchecked")
	public Bill createBill(int billerId, int accountId,
			String billNumber, Date billDate, Date billDueDate,
			double billAmount) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Biller biller = null;
		Account account = null;
		Status status = null;
		Bill bill = null;
		List<Bill> bills = null;
		Query query = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session
					.createQuery("FROM Bill a WHERE a.billNumber = :billNumber");
			query.setString("billNumber", billNumber);
			bills = query.list();
			if (!bills.isEmpty()) {
				throw new ApplicationException(BillException.EXISTS);
			} else {
				biller = new Biller();
				biller.setId(billerId);

				account = new Account();
				account.setId(accountId);
				
				status = (Status) session.createQuery("FROM Status s WHERE s.status = :new").setString("new", COLUMNS.STATUSNEW.getColumn()).uniqueResult();
				
				if (status == null) {
					status = new Status();
					status.setId(Integer.parseInt(COLUMNS.STATUSNEWID.getColumn()));
					status.setStatus(COLUMNS.STATUSNEW.getColumn());
					session.save(status);
				}
				
				bill = new Bill();
				bill.setBillNumber(billNumber);
				bill.setBillDate(new java.sql.Date(billDate.getTime()));
				bill.setBillDueDate(new java.sql.Date(billDueDate.getTime()));
				bill.setBillAmount(billAmount);

				bill.setBiller(biller);
				bill.setAccount(account);
				bill.setStatus(status);

				session.save(bill);
				transaction.commit();
			}
		} catch (ConstraintViolationException c) {
			transaction.rollback();
			throw new ApplicationException(BillException.EXISTS);
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return bill;
	}

	public Bill getBill(int billId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Bill bill = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			bill = (Bill) session.get(Bill.class, billId);
			if (bill == null) {
				throw new ApplicationException(BillException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return bill;
	}

	@SuppressWarnings("unchecked")
	public List<Bill> getBills() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Bill> bills = null;
		Bill bill = null;
		Account account = null;
		Biller biller = null;
		Status status = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session
					.createQuery("from Bill as bill left join bill.account acc left join acc.biller left join bill.status");
			List<Object[]> tuples = (List<Object[]>) query.list();
			bills = new ArrayList<Bill>();
			for (Object[] tuple : tuples) {
				bill = (Bill) tuple[0];
				account = (Account) tuple[1];
				biller = (Biller) tuple[2];
				status = (Status) tuple[3];
				account.setBiller(biller);
				bill.setStatus(status);
				bill.setAccount(account);
				bills.add(bill);
			}
			if (bills == null || bills.isEmpty()) {
				throw new ApplicationException(BillException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return bills;
	}

	@SuppressWarnings("unchecked")
	public Bill updateBill(int id, int billerId, int accountId, int statusId,
			String billNumber, Date billDate, Date billDueDate,
			double billAmount) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Biller biller = null;
		Account account = null;
		Status status = null;
		Bill bill = null;
		List<Bill> bills = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bill = (Bill) session.get(Bill.class, id);
			if (bill == null) {
				throw new ApplicationException(BillException.NOTFOUND);
			}
			
			Criteria criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq(COLUMNS.BILLNUMBER.getColumn(), billNumber));
			bills = criteria.list();
			if (!bills.isEmpty()) {
				throw new ApplicationException(BillException.EXISTS);
			}

			biller = new Biller();
			biller.setId(billerId);

			account = new Account();
			account.setId(accountId);

			status = new Status();
			status.setId(statusId);

			bill.setBillNumber(billNumber);
			bill.setBillDate(new java.sql.Date(billDate.getTime()));
			bill.setBillDueDate(new java.sql.Date(billDueDate.getTime()));
			bill.setBillAmount(billAmount);

			bill.setBiller(biller);
			bill.setAccount(account);
			bill.setStatus(status);

			session.update(bill);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return bill;
	}

	public boolean deleteBill(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bill = (Bill) session.get(Bill.class, id);
			if (bill == null) {
				throw new ApplicationException(BillException.NOTFOUND);
			}
			session.delete(bill);
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