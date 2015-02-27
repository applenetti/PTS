package com.pts.exception;

public enum CardException implements ErrorCode {

	NOTFOUND(404, "Card Not Found!"), EXISTS(2, "Card exists!");

	private final int errorCode;
	private final String errorMessage;
	
	private CardException(int errorCode, String errorMessage) {
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
