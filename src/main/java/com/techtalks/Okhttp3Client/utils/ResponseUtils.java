package com.techtalks.Okhttp3Client.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ResponseUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userMessage;
	private String developerMessage;
	private int statusCode;

	public ResponseUtils build(int statusCode, String userMessage, String developerMessage) {
		this.statusCode = statusCode;
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;
		return this;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
