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
import com.pts.exception.BankException;
import com.pts.exception.SystemError;
import com.pts.pojo.Bank;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class BankDAO {
	
	public Bank createBank(String bankName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bank bank = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq(COLUMNS.NAME.getColumn(), bankName));
			List<?> banks = criteria.list();
			if (banks.isEmpty()) {
				bank = new Bank();
				bank.setName(bankName);
				session.save(bank);
				transaction.commit();
			} else {
				throw new ApplicationException(BankException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return bank;
	}
	
	public Bank getBank(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Bank bank = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			bank = (Bank) session.get(Bank.class, id);
			if (bank == null) {
				throw new ApplicationException(BankException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return bank;
	}
	
	public Bank getBank(String bankName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Bank bank = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			bank = (Bank) session.createQuery("FROM  bank b WHERE b.name = :bankName");
			if (bank == null) {
				throw new ApplicationException(BankException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return bank;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bank> getBanks() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Bank> banks = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Bank");
			banks = query.list();
			if (banks == null || banks.isEmpty()) {
				throw new ApplicationException(BankException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return banks;
	}
	
	@SuppressWarnings("unchecked")
	public Bank updateBank(int id, String bankName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bank bank = null;
		List<Bank> banks = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bank = (Bank) session.get(Bank.class, id);
			if (bank == null) {
				throw new ApplicationException(BankException.NOTFOUND);
			}
			Criteria criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq(COLUMNS.NAME.getColumn(), bankName));
			banks = criteria.list();
			if (!banks.isEmpty()) {
				throw new ApplicationException(BankException.EXISTS);
			}
			bank.setName(bankName);
			session.update(bank);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return bank;
	}
	
	public boolean deleteBank(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Bank bank = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			bank = (Bank) session.get(Bank.class, id);
			if (bank == null) {
				throw new ApplicationException(BankException.NOTFOUND);
			}
			session.delete(bank);
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