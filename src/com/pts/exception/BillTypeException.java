package com.pts.exception;

public enum BillTypeException implements ErrorCode {

	NOTFOUND(404, "Bill Type Not Found!"), EXISTS(2, "Bill Type exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private BillTypeException(int errorCode, String errorMessage) {
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
