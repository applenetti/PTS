package com.pts.business;

import java.util.List;

import com.pts.dao.BillerDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Biller;

public class BillerBusiness {

	public Biller getBiller(int id) throws ApplicationException {
		Biller biller = null;
		try {
			biller = new BillerDAO().getBiller(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return biller;
	}
	
	public Biller getBiller(String billerName) throws ApplicationException {
		Biller biller = null;
		try {
			biller = new BillerDAO().getBiller(billerName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return biller;
	}
	
	public List<Biller> getBillers() throws ApplicationException {
		List<Biller> billers = null;
		try {
			billers = new BillerDAO().getBillers();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billers;
	}

	public Biller createBiller(String billerName) throws ApplicationException {
		Biller biller = null;
		try {
			biller = new BillerDAO().createBiller(billerName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public Biller updateBiller(int id, String billerName) throws ApplicationException {
		Biller biller = null;
		try {
			biller = new BillerDAO().updateBiller(id, billerName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public boolean deleteBiller(int id) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new BillerDAO().deleteBiller(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
