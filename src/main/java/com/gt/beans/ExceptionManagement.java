package com.gt.beans;


public class ExceptionManagement extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	private String errorMassage;
	
	public ExceptionManagement() {
	}
	
	public ExceptionManagement(int errorCode, String errorMassage) {
		this.errorCode = errorCode;
		this.errorMassage = errorMassage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMassage() {
		return errorMassage;
	}
	
}
