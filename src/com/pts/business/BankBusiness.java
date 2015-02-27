package com.pts.business;

import java.util.List;

import com.pts.dao.BankDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Bank;

public class BankBusiness {

	public Bank getBank(int id) throws ApplicationException {
		Bank bank = null;
		try {
			bank = new BankDAO().getBank(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return bank;
	}
	
	public Bank getBank(String bankName) throws ApplicationException {
		Bank bank = null;
		try {
			bank = new BankDAO().getBank(bankName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return bank;
	}
	
	public List<Bank> getBanks() throws ApplicationException {
		List<Bank> banks = null;
		try {
			banks = new BankDAO().getBanks();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return banks;
	}

	public Bank createBank(String bankName) throws ApplicationException {
		Bank bank = null;
		try {
			bank = new BankDAO().createBank(bankName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return bank;
	}

	public Bank updateBank(int id, String bankName) throws ApplicationException {
		Bank bank = null;
		try {
			bank = new BankDAO().updateBank(id, bankName);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return bank;
	}

	public boolean deleteBank(int id) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new BankDAO().deleteBank(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
