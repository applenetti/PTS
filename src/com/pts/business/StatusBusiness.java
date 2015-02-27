package com.pts.business;

import java.util.List;

import com.pts.dao.StatusDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Status;

public class StatusBusiness {

	public Status getStatus(int id) throws ApplicationException {
		Status status = null;
		try {
			status = new StatusDAO().getStatus(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return status;
	}
	
	public Status getStatus(String statusDesc) throws ApplicationException {
		Status status = null;
		try {
			status = new StatusDAO().getStatus(statusDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return status;
	}
	
	public List<Status> getStatuss() throws ApplicationException {
		List<Status> statuss = null;
		try {
			statuss = new StatusDAO().getStatuss();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return statuss;
	}

	public Status createStatus(String statusDesc) throws ApplicationException {
		Status status = null;
		try {
			status = new StatusDAO().createStatus(statusDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return status;
	}

	public Status updateStatus(int id, String statusDesc) throws ApplicationException {
		Status status = null;
		try {
			status = new StatusDAO().updateStatus(id, statusDesc);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return status;
	}

	public boolean deleteStatus(int id) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new StatusDAO().deleteStatus(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
