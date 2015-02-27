package com.pts.business;

import java.util.Date;
import java.util.List;

import com.pts.dao.BillDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Bill;

public class BillBusiness {

	public Bill getBill(int id) throws ApplicationException {
		Bill bill = null;
		try {
			bill = new BillDAO().getBill(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return bill;
	}
	
	public List<Bill> getBills() throws ApplicationException {
		List<Bill> bills = null;
		try {
			bills = new BillDAO().getBills();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return bills;
	}

	public Bill createBill(int billTypeId, int billSubTypeId, int billerId, int accountId, String billNumber, Date billDate, Date billDueDate, double billAmount) throws ApplicationException {
		Bill bill = null;
		try {
			bill = new BillDAO().createBill(billTypeId, billSubTypeId, billerId, accountId, billNumber, billDate, billDueDate, billAmount);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return bill;
	}

	public Bill updateBill(int id, String billId, String billNumber, String username, String password, int billerId) throws ApplicationException {
		Bill bill = null;
		try {
			bill = new BillDAO().updateBill(id, billId, billNumber, username, password, billerId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return bill;
	}

	public boolean deleteBill(int billId) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new BillDAO().deleteBill(billId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
