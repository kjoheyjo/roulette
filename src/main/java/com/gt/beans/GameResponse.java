package com.gt.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GameResponse")
public class GameResponse {

	private Header header =new Header();
	public GameResponse() {
		
	}
	public Header getHeader() {
		return header;
	}
	
	@XmlElement
	public void setHeader(Header header) {
		this.header = header;
	}
	
}
