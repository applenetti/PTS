package com.pts.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.pts.exception.ApplicationException;
import com.pts.exception.BankException;
import com.pts.exception.BillSubTypeException;
import com.pts.exception.SystemError;
import com.pts.pojo.BillSubType;
import com.pts.pojo.BillType;
import com.pts.util.HibernateUtil;

public class BillSubTypeDAO {
	
	@SuppressWarnings("unchecked")
	public BillSubType createBillSubType(int billTypeId, String billSubTypeDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		List<BillSubType> billSubTypes = null;
		BillType billType = null;
		BillSubType billSubType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session
					.createQuery("FROM BillSubType b WHERE b.billSubType = :billSubTypeDesc AND b.billType = :billTypeId");
			query.setInteger("billTypeId", billTypeId);
			query.setString("billSubTypeDesc", billSubTypeDesc);
			billSubTypes = query.list();
			if (!billSubTypes.isEmpty()) {
				throw new ApplicationException(BankException.EXISTS);
			}
			billSubType = new BillSubType();
			billType = new BillType();
			billType.setId(billTypeId);
			billSubType.setBillType(billType);
			billSubType.setBillSubType(billSubTypeDesc);
			session.save(billSubType);
			transaction.commit();

		} catch (HibernateException he) {
			transaction.rollback();
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billSubType;
	}
	
	public BillSubType getBillSubType(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		BillSubType billSubType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			billSubType = (BillSubType) session.get(BillSubType.class, id);
			if (billSubType == null) {
				throw new ApplicationException(BillSubTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billSubType;
	}
	
	@SuppressWarnings("unchecked")
	public List<BillSubType> getBillSubTypes() throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<BillSubType> billSubTypes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from BillSubType");
			billSubTypes = query.list();
			if (billSubTypes == null || billSubTypes.isEmpty()) {
				throw new ApplicationException(BillSubTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billSubTypes;
	}
	
	@SuppressWarnings("unchecked")
	public List<BillSubType> getBillSubTypes(int billTypeId) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<BillSubType> billSubTypes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			Query query = session.createQuery("from BillSubType a where a.billType = :billTypeId");
			query.setInteger("billTypeId", billTypeId);
			billSubTypes = query.list();
			if (billSubTypes == null || billSubTypes.isEmpty()) {
				throw new ApplicationException(BillSubTypeException.NOTFOUND);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ApplicationException(SystemError.HIBERNATEERROR);
		} finally {
			session.close();
		}
		return billSubTypes;
	}
	
	@SuppressWarnings("unchecked")
	public BillSubType updateBillSubType(int id, int billTypeId, String billSubTypeDesc) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		BillType billType = null;
		BillSubType billSubType = null;
		List<BillSubType> billSubTypes = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			billSubType = (BillSubType) session.get(BillSubType.class, id);
			if (billSubType == null) {
				throw new ApplicationException(BillSubTypeException.NOTFOUND);
			}
			Query query = session.createQuery("from BillSubType a where a.billType = :billTypeId AND a.billSubType = :billSubType");
			query.setInteger("billTypeId", billTypeId);
			query.setString("billSubType", billSubTypeDesc);			
			billSubTypes = query.list();
			if (!billSubTypes.isEmpty()) {
				throw new ApplicationException(BillSubTypeException.EXISTS);
			}
			billType = new BillType();
			billType.setId(billTypeId);
			billSubType.setBillType(billType);
			billSubType.setBillSubType(billSubTypeDesc);
			session.update(billSubType);
			transaction.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return billSubType;
	}
	
	public boolean deleteBillSubType(int id) throws ApplicationException {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		BillSubType billSubType = null;
		try {
			sessionFactory = HibernateUtil.INSTANCE.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			billSubType = (BillSubType) session.get(BillSubType.class, id);
			if (billSubType == null) {
				throw new ApplicationException(BillSubTypeException.NOTFOUND);
			}
			session.delete(billSubType);
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