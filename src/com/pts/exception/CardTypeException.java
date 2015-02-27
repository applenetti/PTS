package com.pts.exception;

public enum CardTypeException implements ErrorCode {

	NOTFOUND(404, "Card Type Not Found!"), EXISTS(2, "Card Type exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private CardTypeException(int errorCode, String errorMessage) {
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
