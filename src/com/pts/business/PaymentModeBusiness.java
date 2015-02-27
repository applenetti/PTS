package com.pts.business;

import java.util.List;

import com.pts.dao.PaymentModeDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.PaymentMode;

public class PaymentModeBusiness {

	public PaymentMode getPaymentMode(int id) throws ApplicationException {
		PaymentMode biller = null;
		try {
			biller = new PaymentModeDAO().getPaymentMode(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return biller;
	}
	
	public List<PaymentMode> getPaymentModes() throws ApplicationException {
		List<PaymentMode> billers = null;
		try {
			billers = new PaymentModeDAO().getPaymentModes();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billers;
	}

	public PaymentMode createPaymentMode(String billerName) throws ApplicationException {
		PaymentMode biller = null;
		try {
			biller = new PaymentModeDAO().createPaymentMode(billerName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public PaymentMode updatePaymentMode(int billerId, String billerName) throws ApplicationException {
		PaymentMode biller = null;
		try {
			biller = new PaymentModeDAO().updatePaymentMode(billerId, billerName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public boolean deletePaymentMode(int billerId) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new PaymentModeDAO().deletePaymentMode(billerId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
