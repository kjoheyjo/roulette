package com.gt.beans;

public class ExceptionMessage {
	
	public static final int ERROR_CODE_NOT_FOUND = 404;
	public static final String ERROR_MSG_NOT_FOUND = "Requested URl not found.";
	public static final int ERROR_CODE_MISSING_PARAMETERS = 800;
	public static final String ERROR_MSG_MISSING_PARAMETERS = "Server did not receive proper parameters";
	public static final int ERROR_CODE_INTERNAL_SERVER = 801;
	public static final String ERROR_MSG_INTERNAL_SERVER = "Something Went Wrong. Please contact admin!";
	public static final int ERROR_CODE_MALFORMED_XML = 802;
	public static final String ERROR_MSG_MALFORMED_XML = "Malformed XML, please check XML body of request";
	public static final int ERROR_CODE_MALFORMED_URL = 803;
	public static final String ERROR_MSG_MALFORMED_URL = "Malformed URL, please check URL";
	public static final int ERROR_CODE_API_ERROR = 804;
	public static final String ERROR_MSG_API_ERROR = "Error while calling platform API.";
	public static final int ERROR_CODE_DEBIT_FAILED = 805;
	public static final String ERROR_MSG_DEBIT_FAILED = "Debit transaction failed. check balance.";


}
