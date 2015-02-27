package com.pts.exception;

public enum PaymentModeException implements ErrorCode {

	NOTFOUND(404, "Payment Not Found!"), EXISTS(2, "Payment exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private PaymentModeException(int errorCode, String errorMessage) {
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
