package com.pts.exception;

public enum AccountException implements ErrorCode {

	NOTFOUND(404, "Account Not Found!"), EXISTS(2, "Account exists!"), ACCOUNTIDEXISTS(3, "Account ID exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private AccountException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}	

}
