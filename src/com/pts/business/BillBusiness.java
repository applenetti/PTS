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

	public Bill createBill(int billerId, int accountId, int statusId,
			String billNumber, Date billDate, Date billDueDate,
			double billAmount) throws ApplicationException {
		Bill bill = null;
		try {
			bill = new BillDAO().createBill(billerId, accountId, statusId,
					billNumber, billDate, billDueDate,
					billAmount);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return bill;
	}

	public Bill updateBill(int id, int billerId, int accountId, int statusId,
			String billNumber, Date billDate, Date billDueDate,
			double billAmount) throws ApplicationException {
		Bill bill = null;
		try {
			bill = new BillDAO().updateBill(id, billerId, accountId, statusId,
					billNumber, billDate, billDueDate,
					billAmount);
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
