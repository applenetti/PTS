package com.pts.exception;

public enum BillSubTypeException implements ErrorCode {

	NOTFOUND(404, "Bill Sub Type Not Found!"), EXISTS(2, "Bill Sub Type exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private BillSubTypeException(int errorCode, String errorMessage) {
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
