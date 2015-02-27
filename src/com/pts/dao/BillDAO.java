package com.pts.dao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.pts.exception.ApplicationException;
import com.pts.exception.BillException;
import com.pts.exception.SystemError;
import com.pts.pojo.Account;
import com.pts.pojo.Bill;
import com.pts.pojo.BillSubType;
import com.pts.pojo.BillType;
import com.pts.pojo.Biller;
import com.pts.util.HibernateUtil;

public class BillDAO {
	
	@SuppressWarnings("unchecked")
	public Bill createBill(int billTypeId, int billSubTypeId, int billerId, int accountId, String billNumber, Date billDate, Date billDueDate, double billAmount) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;
		BillType billType = null;
		BillSubType billSubType = null;		
		Biller biller = null;
		Account account = null;
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
				billType = new BillType();
				billType.setId(billTypeId);
				billSubType = new BillSubType();
				billSubType.setId(billSubTypeId);
				biller = new Biller();
				biller.setId(billerId);
				account = new Account();
				account.setId(accountId);
				
				bill = new Bill();
				bill.setBillNumber(billNumber);
				bill.setBillDate(new java.sql.Date(billDate.getTime()));
				bill.setDueDate(new java.sql.Date(billDueDate.getTime()));
				bill.setBillAmount(billAmount);
				
				bill.setBillType(billType);
				bill.setBillSubType(billSubType);
				bill.setAccount(account);
				
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
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Bill as bill left join bill.account acc left join acc.biller");
			List<Object[]> tuples = (List<Object[]>) query.list();
			bills = new ArrayList<Bill>();
			for(Object[] tuple : tuples) {
			    bill = (Bill) tuple[0];
			    account = (Account) tuple[1];
			    biller = (Biller) tuple[2];
			    account.setBiller(biller);
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
	
	public Bill updateBill(int id, String billId, String billNumber, String username, String password, int billerId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bill = (Bill) session.get(Bill.class, id);
			if (bill == null) {
				throw new ApplicationException(BillException.NOTFOUND);
			}
			biller = new Biller();
			biller.setId(billerId);
			/*bill.setBillId(billId);
			bill.setBillNumber(billNumber);
			bill.setUsername(username);
			bill.setPassword(password);
			bill.setBiller(biller);*/
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
	
	public boolean deleteBill(int billId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bill bill = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bill = (Bill) session.get(Bill.class, billId);
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