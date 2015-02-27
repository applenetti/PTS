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
import com.pts.exception.StatusException;
import com.pts.exception.SystemError;
import com.pts.pojo.Status;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class StatusDAO {
	
	public Status createStatus(String statusDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Status status = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Status.class);
			criteria.add(Restrictions.eq(COLUMNS.STATUS.getColumn(), statusDesc));
			List<?> statuss = criteria.list();
			if (statuss.isEmpty()) {
				status = new Status();
				status.setStatus(statusDesc);
				session.save(status);
				transaction.commit();
			} else {
				throw new ApplicationException(StatusException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return status;
	}
	
	public Status getStatus(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Status status = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			status = (Status) session.get(Status.class, id);
			if (status == null) {
				throw new ApplicationException(StatusException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return status;
	}
	
	public Status getStatus(String statusDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Status status = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			status = (Status) session.createQuery("FROM status b WHERE b.id = :statusDesc");
			if (status == null) {
				throw new ApplicationException(StatusException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public List<Status> getStatuss() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Status> statuss = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Status");
			statuss = query.list();
			if (statuss == null || statuss.isEmpty()) {
				throw new ApplicationException(StatusException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return statuss;
	}
	
	public Status updateStatus(int id, String statusDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Status status = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			status = (Status) session.get(Status.class, id);
			if (status == null) {
				throw new ApplicationException(StatusException.NOTFOUND);
			}
			status.setStatus(statusDesc);
			session.update(status);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return status;
	}
	
	public boolean deleteStatus(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		Status status = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			status = (Status) session.get(Status.class, id);
			if (status == null) {
				throw new ApplicationException(StatusException.NOTFOUND);
			}
			session.delete(status);
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