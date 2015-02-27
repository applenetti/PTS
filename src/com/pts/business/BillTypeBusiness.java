package com.pts.business;

import java.util.List;

import com.pts.dao.BillTypeDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.BillType;

public class BillTypeBusiness {

	public BillType getBillType(int id) throws ApplicationException {
		BillType biller = null;
		try {
			biller = new BillTypeDAO().getBillType(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return biller;
	}
	
	public List<BillType> getBillTypes() throws ApplicationException {
		List<BillType> billers = null;
		try {
			billers = new BillTypeDAO().getBillTypes();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billers;
	}

	public BillType createBillType(String billTypeDesc) throws ApplicationException {
		BillType biller = null;
		try {
			biller = new BillTypeDAO().createBillType(billTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public BillType updateBillType(int id, String billTypeDesc) throws ApplicationException {
		BillType biller = null;
		try {
			biller = new BillTypeDAO().updateBillType(id, billTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public boolean deleteBillType(int id) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new BillTypeDAO().deleteBillType(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
