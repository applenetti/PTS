package com.pts.exception;

public enum SystemError implements ErrorCode {

	HIBERNATEERROR(1, "See logs for more details");

	private final int errorCode;
	private final String errorMessage;

	private SystemError(int errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = "";
	}
	
	private SystemError(int errorCode, String errorMessage) {
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
