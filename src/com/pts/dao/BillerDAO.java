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
import com.pts.exception.BillerException;
import com.pts.exception.SystemError;
import com.pts.pojo.Biller;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class BillerDAO {
	
	public Biller createBiller(String billerName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Biller.class);
			criteria.add(Restrictions.eq(COLUMNS.NAME.getColumn(), billerName));
			List<?> billers = criteria.list();
			if (billers.isEmpty()) {
				biller = new Biller();
				biller.setName(billerName);
				session.save(biller);
				transaction.commit();
			} else {
				throw new ApplicationException(BillerException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return biller;
	}
	
	public Biller getBiller(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			biller = (Biller) session.get(Biller.class, id);
			if (biller == null) {
				throw new ApplicationException(BillerException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return biller;
	}
	
	public Biller getBiller(String billerName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			biller = (Biller) session.createQuery("FROM biller b WHERE b.id = :billerName");
			if (biller == null) {
				throw new ApplicationException(BillerException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return biller;
	}
	
	@SuppressWarnings("unchecked")
	public List<Biller> getBillers() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Biller> billers = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Biller");
			billers = query.list();
			if (billers == null || billers.isEmpty()) {
				throw new ApplicationException(BillerException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billers;
	}
	
	public Biller updateBiller(int id, String billerName) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			biller = (Biller) session.get(Biller.class, id);
			if (biller == null) {
				throw new ApplicationException(BillerException.NOTFOUND);
			}
			Criteria criteria = session.createCriteria(Biller.class);
			criteria.add(Restrictions.eq(COLUMNS.NAME.getColumn(), billerName));
			List<?> billers = criteria.list();
			if (!billers.isEmpty()) {
				throw new ApplicationException(BillerException.EXISTS);
			}
			biller.setName(billerName);
			session.update(biller);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return biller;
	}
	
	public boolean deleteBiller(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Biller biller = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			biller = (Biller) session.get(Biller.class, id);
			if (biller == null) {
				throw new ApplicationException(BillerException.NOTFOUND);
			}
			session.delete(biller);
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