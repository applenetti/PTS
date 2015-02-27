package com.pts.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.pts.exception.AccountException;
import com.pts.exception.ApplicationException;
import com.pts.exception.SystemError;
import com.pts.pojo.Account;
import com.pts.pojo.Biller;
import com.pts.util.HibernateUtil;

public class AccountDAO {
	
	@SuppressWarnings("unchecked")
	public Account createAccount(String accountId, String accountNumber, String username, String password, int billerId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Account account = null;
		Biller biller = null;
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
				account = new Account();
				account.setAccountId(accountId);
				account.setAccountNumber(accountNumber);
				account.setUsername(username);
				account.setPassword(password);
				account.setBiller(biller);
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
		List<Account> accounts = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Account a where a.biller = :billerId");
			query.setInteger("billerId", billerId);
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
	
	public Account updateAccount(int id, String accountId, String accountNumber, String username, String password, int billerId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Account account = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			account = (Account) session.get(Account.class, id);
			if (account == null) {
				throw new ApplicationException(AccountException.NOTFOUND);
			}
			biller = new Biller();
			biller.setId(billerId);
			account.setAccountId(accountId);
			account.setAccountNumber(accountNumber);
			account.setUsername(username);
			account.setPassword(password);
			account.setBiller(biller);
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
	
	public boolean deleteAccount(int accountId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Account account = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			account = (Account) session.get(Account.class, accountId);
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