package com.techtalks.Okhttp3Client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtalks.Okhttp3Client.controller.HttpCallsUsingOkhttp3ApplicationController;
import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;
import com.techtalks.Okhttp3Client.service.HttpCallsUsingOkhttp3ApplicationService;
import com.techtalks.Okhttp3Client.utils.Constants;
import com.techtalks.Okhttp3Client.utils.ResponseUtils;

public class ControllerTest {
	@Mock
	private ResponseUtils responseUtils;

	@Mock
	private HttpCallsUsingOkhttp3ApplicationService service;

	@InjectMocks
	private HttpCallsUsingOkhttp3ApplicationController controller;

	/**
	 * This method to do/set value of a filed which should be done before test case
	 * execution starts
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// add any thing here to to set it up before starting the test case execution
	}

	@Test
	public void controllerTestForOkResponse() {
		HttpRequest request = getValidRequestObject();
		Map<String, String> result = new HashMap<>();
		result.put(Constants.IS_SUCCESS, "true");
		result.put(Constants.STATUS_CODE, "200");
		result.put(Constants.RESPONSE_STR, "Dummy request success");
		Mockito.doReturn(result).when(this.service).executeHttpRequest(request);
		ResponseEntity<Object> response = this.controller.executeHttpCall(request);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void controllerTestForInvalidRequest() {
		HttpRequest request = new HttpRequest();
		Map<String, String> header = new HashMap<>();
		header.put("ContentType", "applicaiton/json");
		header.put("xpath", "array.try.again");
		request.setHeader(header);
		request.setQueryParams(new HashMap<>());
		ResponseEntity<Object> responseEntity = this.controller.executeHttpCall(request);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void controllerTestForNotOk() {
		HttpRequest request = getValidRequestObject();
		Map<String, String> result = new HashMap<>();
		result.put(Constants.IS_SUCCESS, "false");
		result.put(Constants.STATUS_CODE, "500");
		result.put(Constants.RESPONSE_STR, "Dummy request failure");
		Mockito.doReturn(result).when(this.service).executeHttpRequest(request);
		ResponseEntity<Object> response = this.controller.executeHttpCall(request);
		Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	private HttpRequest getValidRequestObject() {
		HttpRequest request = new HttpRequest();
		request.setUrl("http://localhost:8081/v1/dataGenerator/generateData?recordCount=20&dataType=JSON");
		request.setRequestMethod(RequestMethod.POST);
		Map<String, String> header = new HashMap<>();
		header.put("ContentType", "applicaiton/json");
		header.put("xpath", "array.try.again");
		request.setHeader(header);
		request.setQueryParams(new HashMap<>());
		request.setRequestBody(new JSONObject());
		return request;
	}

}
