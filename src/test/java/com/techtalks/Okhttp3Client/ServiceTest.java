package com.techtalks.Okhttp3Client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;
import com.techtalks.Okhttp3Client.restClient.RestClient;
import com.techtalks.Okhttp3Client.service.HttpCallsUsingOkhttp3ApplicationService;
import com.techtalks.Okhttp3Client.utils.Constants;
import org.junit.Assert;

public class ServiceTest {

	@Mock
	private RestClient restClient;

	@Mock
	private HttpCallsUsingOkhttp3ApplicationService service;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void serviceTestForOkResponse() {
		HttpRequest request = new HttpRequest();
		request.setHeader(new HashMap<>());
		request.setUrl("http://google.com");
		request.setQueryParams(new HashMap<>());
		request.setRequestMethod(RequestMethod.POST);
		Map<String, String> result = new HashMap<>();
		result.put(Constants.IS_SUCCESS, "true");
		result.put(Constants.STATUS_CODE, "200");
		result.put(Constants.RESPONSE_STR, "Dummy request success");
		Mockito.doReturn(result).when(this.restClient).executeExternalApi(request);
		Map<String, String> map = this.service.executeHttpRequest(request);
		Assert.assertEquals(true, map.isEmpty());
	}

}
