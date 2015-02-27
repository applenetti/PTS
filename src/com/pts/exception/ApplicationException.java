package com.pts.exception;

public class ApplicationException extends Exception implements ErrorCode {
	
	private static final long serialVersionUID = 1L;
	private ErrorCode errorObject;
	
	public ApplicationException(ErrorCode errorCode) {
		super();
		this.errorObject = errorCode;
	}
	
	@Override
	public int getErrorCode() {
		return errorObject.getErrorCode();
	}

	@Override
	public String getErrorMessage() {
		return errorObject.getErrorMessage();
	}
	
}
