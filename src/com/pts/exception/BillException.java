package com.pts.exception;

public enum BillException implements ErrorCode {

	NOTFOUND(404, "Bill Not Found!"), EXISTS(2, "Bill exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private BillException(int errorCode, String errorMessage) {
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
