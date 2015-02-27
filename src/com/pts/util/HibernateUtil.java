package com.pts.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pts.exception.SystemError;
import com.pts.pojo.Account;
import com.pts.pojo.Bank;
import com.pts.pojo.Bill;
import com.pts.pojo.BillSubType;
import com.pts.pojo.BillType;
import com.pts.pojo.Biller;
import com.pts.pojo.Card;
import com.pts.pojo.CardType;
import com.pts.pojo.PaymentMode;
import com.pts.pojo.Status;

public enum HibernateUtil {

	INSTANCE;

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure()
						.addAnnotatedClass(BillType.class)
						.addAnnotatedClass(BillSubType.class)
						.addAnnotatedClass(Biller.class)
						.addAnnotatedClass(Bank.class)
						.addAnnotatedClass(CardType.class)
						.addAnnotatedClass(Card.class)
						.addAnnotatedClass(Account.class)
						.addAnnotatedClass(Bill.class)
						.addAnnotatedClass(PaymentMode.class)
						.addAnnotatedClass(Status.class)
						.buildSessionFactory();
			}
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new ExceptionInInitializerError(
					SystemError.HIBERNATEERROR.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
