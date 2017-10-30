package com.gt.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Exception")
public class ExceptionManagement extends RuntimeException  {
	
	@XmlAttribute(name="id")
	private static final long serialVersionUID = 1L;
	@XmlElement(name="error_code")
	private int errorCode;
	@XmlElement(name="error_message")
	private String errorMessage;
	
	public ExceptionManagement() {
	}
	
	public ExceptionManagement(int errorCode, String errorMassage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMassage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
}
