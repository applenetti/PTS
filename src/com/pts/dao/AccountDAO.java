package com.pts.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.pts.exception.AccountException;
import com.pts.exception.ApplicationException;
import com.pts.exception.SystemError;
import com.pts.pojo.Account;
import com.pts.pojo.BillSubType;
import com.pts.pojo.BillType;
import com.pts.pojo.Biller;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class AccountDAO {
	
	@SuppressWarnings("unchecked")
	public Account createAccount(int billerId, int billTypeId, int billSubTypeId, String accountId, String mobileNumber, String username, String email) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Account account = null;
		Biller biller = null;
		BillType billType = null;
		BillSubType billSubType = null;		
		List<Account> accounts = null;
		Query query = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			query = session
					.createQuery("FROM Account a WHERE a.accountId = :accountId");
			query.setString("accountId", accountId);
			accounts = query.list();
			if (!accounts.isEmpty()) {
				throw new ApplicationException(AccountException.ACCOUNTIDEXISTS);
			} else {
				biller = new Biller();
				biller.setId(billerId);
				
				billType = new BillType();
				billType.setId(billTypeId);
				
				billSubType = new BillSubType();
				billSubType.setId(billSubTypeId);				
				
				account = new Account();
				account.setAccountId(accountId);
				account.setMobileNumber(mobileNumber);
				account.setUsername(username);
				account.setEmail(email);
				
				account.setBiller(biller);
				account.setBillType(billType);
				account.setBillSubType(billSubType);
				
				session.save(account);
				transaction.commit();
			}
		} catch (ConstraintViolationException c) {
			transaction.rollback();
			throw new ApplicationException(AccountException.EXISTS);			
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return account;
	}
	
	public Account getAccount(int accountId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Account account = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			account = (Account) session.get(Account.class, accountId);
			if (account == null) {
				throw new ApplicationException(AccountException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return account;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Account> accounts = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Account");
			accounts = query.list();
			if (accounts == null || accounts.isEmpty()) {
				throw new ApplicationException(AccountException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return accounts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAccountsById(int billerId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Account account = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Account a where a.biller = :billerId");
			query.setInteger("billerId", billerId);
			List<Object[]> objects = (List<Object[]>) query.list();
			for (Object object : objects) {
				account = (Account) object;
				accounts.add(account);
				/*account = (Account) object[0];
				biller = (Biller) object[1];
				billType = (BillType) object[2];
				billSubType = (BillSubType) object[3];
				account.setBiller(biller);
				account.setBillType(billType);
				account.setBillSubType(billSubType);				
				accounts.add(account);*/
			}
			if (accounts == null || accounts.isEmpty()) {
				throw new ApplicationException(AccountException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return accounts;
	}
	
	@SuppressWarnings("unchecked")
	public Account updateAccount(int id, int billerId, int billTypeId, int billSubTypeId, String accountId, String mobileNumber, String username, String email) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		BillType billType = null;
		BillSubType billSubType = null;
		Biller biller = null;
		Account account = null;
		List<Account> accounts = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			account = (Account) session.get(Account.class, id);
			if (account == null) {
				throw new ApplicationException(AccountException.NOTFOUND);
			}
			
			Criteria criteria = session.createCriteria(Account.class);
			criteria.add(Restrictions.eq(COLUMNS.ACCOUNTID.getColumn(), accountId));
			accounts = criteria.list();
			if (!accounts.isEmpty()) {
				throw new ApplicationException(AccountException.EXISTS);
			}

			biller = new Biller();
			biller.setId(billerId);
			
			billType = new BillType();
			billType.setId(billTypeId);
			
			billSubType = new BillSubType();
			billSubType.setId(billSubTypeId);				
			
			account.setAccountId(accountId);
			account.setMobileNumber(mobileNumber);
			account.setUsername(username);
			account.setEmail(email);
			
			account.setBiller(biller);
			account.setBillType(billType);
			account.setBillSubType(billSubType);
			
			session.update(account);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return account;
	}
	
	public boolean deleteAccount(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Account account = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			account = (Account) session.get(Account.class, id);
			if (account == null) {
				throw new ApplicationException(AccountException.NOTFOUND);
			}
			session.delete(account);
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