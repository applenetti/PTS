package com.pts.exception;

public enum BankException implements ErrorCode {

	NOTFOUND(404, "Bank Not Found!"), EXISTS(2, "Bank exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private BankException(int errorCode, String errorMessage) {
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
