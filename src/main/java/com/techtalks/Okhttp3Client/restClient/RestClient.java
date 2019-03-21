package com.techtalks.Okhttp3Client.restClient;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;

@Component
public interface RestClient {
	//public Response externalApiCall(HttpRequest request);
	public Map<String,String> executeExternalApi(HttpRequest request);

}
