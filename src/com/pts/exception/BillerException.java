package com.pts.exception;

public enum BillerException implements ErrorCode {

	NOTFOUND(404, "Biller Not Found!"), EXISTS(2, "Biller exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private BillerException(int errorCode, String errorMessage) {
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
