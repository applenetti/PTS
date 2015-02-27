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
import com.pts.exception.BillTypeException;
import com.pts.exception.SystemError;
import com.pts.pojo.BillType;
import com.pts.util.COLUMNS;
import com.pts.util.HibernateUtil;

public class BillTypeDAO {
	
	public BillType createBillType(String billTypeDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		BillType billType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(BillType.class);
			criteria.add(Restrictions.eq(COLUMNS.TYPE.getColumn(), billTypeDesc));
			List<?> billTypes = criteria.list();
			if (billTypes.isEmpty()) {
				billType = new BillType();
				billType.setType(billTypeDesc);
				session.save(billType);
				transaction.commit();
			} else {
				throw new ApplicationException(BillTypeException.EXISTS);
			}
		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billType;
	}
	
	public BillType getBillType(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		BillType billType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			billType = (BillType) session.get(BillType.class, id);
			if (billType == null) {
				throw new ApplicationException(BillTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billType;
	}
	
	@SuppressWarnings("unchecked")
	public List<BillType> getBillTypes() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<BillType> billTypes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from BillType");
			billTypes = query.list();
			if (billTypes == null || billTypes.isEmpty()) {
				throw new ApplicationException(BillTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billTypes;
	}
	
	public BillType updateBillType(int id, String billTypeDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		BillType billType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			billType = (BillType) session.get(BillType.class, id);
			if (billType == null) {
				throw new ApplicationException(BillTypeException.NOTFOUND);
			}
			billType.setType(billTypeDesc);
			session.update(billType);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return billType;
	}
	
	public boolean deleteBillType(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		BillType billType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			billType = (BillType) session.get(BillType.class, id);
			if (billType == null) {
				throw new ApplicationException(BillTypeException.NOTFOUND);
			}
			session.delete(billType);
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