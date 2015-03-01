package com.pts.business;

import java.util.List;

import com.pts.dao.AccountDAO;
import com.pts.exception.ApplicationException;
import com.pts.pojo.Account;

public class AccountBusiness {

	public Account getAccount(int id) throws ApplicationException {
		Account account = null;
		try {
			account = new AccountDAO().getAccount(id);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return account;
	}
	
	public List<Account> getAccounts() throws ApplicationException {
		List<Account> accounts = null;
		try {
			accounts = new AccountDAO().getAccounts();
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return accounts;
	}
	
	public List<Account> getAccountsById(int accountId) throws ApplicationException {
		List<Account> accounts = null;
		try {
			accounts = new AccountDAO().getAccountsById(accountId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;	
		}
		return accounts;
	}

	public Account createAccount(int billerId, int billTypeId, int billSubTypeId, String accountId, String mobileNumber, String username, String email) throws ApplicationException {
		Account account = null;
		try {
			account = new AccountDAO().createAccount(billerId, billTypeId, billSubTypeId, accountId, mobileNumber, username, email);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return account;
	}

	public Account updateAccount(int id, int billerId, int billTypeId, int billSubTypeId, String accountId, String mobileNumber, String username, String email) throws ApplicationException {
		Account account = null;
		try {
			account = new AccountDAO().updateAccount(id, billerId, billTypeId, billSubTypeId, accountId, mobileNumber, username, email);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return account;
	}

	public boolean deleteAccount(int accountId) throws ApplicationException {
		boolean isDeleted = false;
		try {
			isDeleted = new AccountDAO().deleteAccount(accountId);
		} catch (ApplicationException e) {
			System.out.println("Error Code: " + e.getErrorCode() + " , Error Message: " + e.getErrorMessage());
			throw e;
		}
		return isDeleted;
	}

}
