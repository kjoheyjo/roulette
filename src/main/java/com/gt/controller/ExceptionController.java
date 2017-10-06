package com.gt.controller;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gt.beans.ExceptionManagement;
import com.gt.beans.ExceptionMassage;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
	
	/*@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public ExceptionManagement errorResponseMissingParameter() {
		ExceptionManagement errorMsg = new ExceptionManagement(ExceptionMassage.ERROR_CODE_MISSING_PARAMETERS,ExceptionMassage.ERROR_MSG_MISSING_PARAMETERS);
		return errorMsg;
	}*/
	
	@ExceptionHandler(ExceptionManagement.class)
	@ResponseBody
	public ExceptionManagement errorResponse( ExceptionManagement errorMsg) {
		/*Error errorMsg = new Error();
		errorMsg.setErrorCode(errorCode);
		errorMsg.setErrorMassage(errorMassage);*/
		return errorMsg;
	}
	

}
