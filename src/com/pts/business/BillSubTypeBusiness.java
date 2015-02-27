package com.pts.business;

import java.util.List;

import com.pts.dao.BillSubTypeDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.BillSubType;

public class BillSubTypeBusiness {

	public BillSubType getBillSubType(int id) throws ApplicationException {
		BillSubType biller = null;
		try {
			biller = new BillSubTypeDAO().getBillSubType(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return biller;
	}
	
	public List<BillSubType> getBillSubTypes() throws ApplicationException {
		List<BillSubType> billers = null;
		try {
			billers = new BillSubTypeDAO().getBillSubTypes();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billers;
	}
	
	public List<BillSubType> getBillSubTypes(int typeId) throws ApplicationException {
		List<BillSubType> billers = null;
		try {
			billers = new BillSubTypeDAO().getBillSubTypes(typeId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return billers;
	}

	public BillSubType createBillSubType(int typeId, String billSubTypeDesc) throws ApplicationException {
		BillSubType biller = null;
		try {
			biller = new BillSubTypeDAO().createBillSubType(typeId, billSubTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public BillSubType updateBillSubType(int id, int billTypeId, String billSubTypeDesc) throws ApplicationException {
		BillSubType biller = null;
		try {
			biller = new BillSubTypeDAO().updateBillSubType(id, billTypeId, billSubTypeDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return biller;
	}

	public boolean deleteBillSubType(int id) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new BillSubTypeDAO().deleteBillSubType(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
