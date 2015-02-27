package com.pts.exception;

public enum StatusException implements ErrorCode {

	NOTFOUND(404, "Status Not Found!"), EXISTS(2, "Status exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private StatusException(int errorCode, String errorMessage) {
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
