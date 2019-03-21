package com.techtalks.Okhttp3Client.utils;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;

@Component
public class HttpCallsUsingOkhttp3ApplicationUtils {

	public JSONObject getRequestBody(HttpRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JSONObject object = mapper.convertValue(request.getRequestBody(), JSONObject.class);
			return object;
		} catch (Exception ex) {
			throw new HttpClientException(ex.getMessage());
		}
	}

}
