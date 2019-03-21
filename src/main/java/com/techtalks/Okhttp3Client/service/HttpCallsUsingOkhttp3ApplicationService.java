package com.techtalks.Okhttp3Client.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;
import com.techtalks.Okhttp3Client.restClient.RestClient;

@Component
public class HttpCallsUsingOkhttp3ApplicationService {
	@Autowired
	private RestClient restClient;
	
	public Map<String,String> executeHttpRequest(HttpRequest request) {
		
		Map<String,String> resultMap = this.restClient.executeExternalApi(request);
		
		return resultMap;
		
	}

}
