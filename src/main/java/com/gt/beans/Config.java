package com.gt.beans;

import org.springframework.stereotype.Component;

@Component
public class Config {
	private String apiBaseUrl;
	
	public Config(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}
}
