package com.pts.business;

import java.sql.Date;
import java.util.List;

import com.pts.dao.PaymentDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Payment;

public class PaymentBusiness {

	public Payment getPayment(int id) throws ApplicationException {
		Payment payment = null;
		try {
			payment = new PaymentDAO().getPayment(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return payment;
	}
	
	public List<Payment> getPayments() throws ApplicationException {
		List<Payment> payments = null;
		try {
			payments = new PaymentDAO().getPayments();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return payments;
	}

	public Payment createPayment(int billId, int statusId, int paymentModeId, Date paymentDate, double paidAmount) throws ApplicationException {
		Payment payment = null;
		try {
			payment = new PaymentDAO().createPayment(billId, statusId, paymentModeId, paymentDate, paidAmount);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return payment;
	}

	public Payment updatePayment(int id, int billId, int statusId, int paymentModeId, Date paymentDate, double paidAmount) throws ApplicationException {
		Payment payment = null;
		try {
			payment = new PaymentDAO().updatePayment(id, billId, statusId, paymentModeId, paymentDate, paidAmount);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return payment;
	}

	public boolean deletePayment(int paymentId) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new PaymentDAO().deletePayment(paymentId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
