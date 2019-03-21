package com.techtalks.Okhttp3Client;

import static org.mockito.ArgumentMatchers.anyObject;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;
import com.techtalks.Okhttp3Client.restClient.OkHttp3RestClient;
import com.techtalks.Okhttp3Client.restClient.RestClientImpl;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RestClientImplTest {

	@InjectMocks
	private RestClientImpl restClient;

	@Mock
	private OkHttp3RestClient okHttp3Client;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	private HttpRequest initializeHttpRequest(RequestMethod requestMethod) {
		HttpRequest request = new HttpRequest();
		request.setHeader(new HashMap<>());
		request.setUrl("http://fakeexx.com");
		request.setQueryParams(new HashMap<>());
		request.setRequestMethod(requestMethod);
		JSONObject requestBodyJson = new JSONObject();
		try {
			requestBodyJson.put("key1", "value1");
			requestBodyJson.put("key2", "value2");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		request.setRequestBody(requestBodyJson);
		return request;
	}

	private Response returnFakeResponse() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("key1", "value1");
			obj.put("key2", "value2");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		ResponseBody rb = ResponseBody.create(MediaType.parse("application/json"), obj.toString());
		Request fakeRequest = new Request.Builder().url("http://abc.com").build();
		Response fakeResponse = new Response.Builder().code(200).request(fakeRequest).protocol(Protocol.HTTP_1_1)
				.body(rb).build();
		return fakeResponse;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void restClientImplTestForGetMethod() {
		Response fakeResponse = returnFakeResponse();
		Mockito.doReturn(fakeResponse).when(this.okHttp3Client).makeHttpCall(anyObject());
		Response actualResponse = this.restClient.externalApiCall(initializeHttpRequest(RequestMethod.GET));
		Assert.assertEquals(fakeResponse.code(), actualResponse.code());

	}

	@Test
	public void restClientImplTestForPostMethod() {
		ResponseBody fakeResponseBody = ResponseBody.create(MediaType.parse("application/json"), "message");
		Request fakeRequest = new Request.Builder().url("http://fakeexx.com").build();
		Response fakeResponse = new Response.Builder().code(200).request(fakeRequest).protocol(Protocol.HTTP_1_1)
				.body(fakeResponseBody).build();
		Mockito.doReturn(fakeResponse).when(this.okHttp3Client).makeHttpCall(anyObject());
		Response actualResponse = this.restClient.externalApiCall(initializeHttpRequest(RequestMethod.POST));
		Assert.assertEquals(fakeResponse.code(), actualResponse.code());

	}

	@Test
	public void restClientImplTestForPutMethod() {
		ResponseBody fakeResponseBody = ResponseBody.create(MediaType.parse("application/json"), "message");
		Request fakeRequest = new Request.Builder().url("http://fakeexx.com").build();
		Response fakeResponse = new Response.Builder().code(200).request(fakeRequest).protocol(Protocol.HTTP_1_1)
				.body(fakeResponseBody).build();
		Mockito.doReturn(fakeResponse).when(this.okHttp3Client).makeHttpCall(anyObject());
		Response actualResponse = this.restClient.externalApiCall(initializeHttpRequest(RequestMethod.PUT));
		Assert.assertEquals(fakeResponse.code(), actualResponse.code());

	}

	@Test
	public void restClientImplTestForDeleteMethod() {
		Response fakeResponse = returnFakeResponse();
		Mockito.doReturn(fakeResponse).when(this.okHttp3Client).makeHttpCall(anyObject());
		Response actualResponse = this.restClient.externalApiCall(initializeHttpRequest(RequestMethod.DELETE));
		Assert.assertEquals(fakeResponse.code(), actualResponse.code());

	}

	@Test
	public void restClientImplTestForHeadMethod() {
		Response fakeResponse = returnFakeResponse();
		Mockito.doReturn(fakeResponse).when(this.okHttp3Client).makeHttpCall(anyObject());
		Response actualResponse = this.restClient.externalApiCall(initializeHttpRequest(RequestMethod.HEAD));
		Assert.assertEquals(fakeResponse.code(), actualResponse.code());
	}

	@Test
	public void restClientImplTestForexecuteExternalApi() {
		try {
			Response response = returnFakeResponse();
			RestClientImpl restClient2 = Mockito.spy(new RestClientImpl());
			Mockito.doReturn(response).when(restClient2).externalApiCall(anyObject());
			Mockito.doReturn(response).when(this.okHttp3Client).makeHttpCall(anyObject());
			Map<String, String> map = this.restClient.executeExternalApi(initializeHttpRequest(RequestMethod.GET));
			Assert.assertEquals(200, Integer.parseInt(map.get("StatusCode")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
