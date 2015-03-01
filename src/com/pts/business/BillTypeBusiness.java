package com.pts.business;

import java.util.List;

import com.pts.dao.BillTypeDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.BillType;

public class BillTypeBusiness {

	public BillType getBillType(int billTypeId) throws ApplicationException {
		BillType billType = null;
		try {
			billType = new BillTypeDAO().getBillType(billTypeId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billType;
	}
	
	public List<BillType> getBillTypes() throws ApplicationException {
		List<BillType> billTypes = null;
		try {
			billTypes = new BillTypeDAO().getBillTypes();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billTypes;
	}

	public BillType createBillType(String billTypeDesc) throws ApplicationException {
		BillType billType = null;
		try {
			billType = new BillTypeDAO().createBillType(billTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return billType;
	}

	public BillType updateBillType(int billTypeId, String billTypeDesc) throws ApplicationException {
		BillType billType = null;
		try {
			billType = new BillTypeDAO().updateBillType(billTypeId, billTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return billType;
	}

	public boolean deleteBillType(int billTypeId) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new BillTypeDAO().deleteBillType(billTypeId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
