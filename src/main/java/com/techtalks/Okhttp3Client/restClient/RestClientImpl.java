package com.techtalks.Okhttp3Client.restClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;
import com.techtalks.Okhttp3Client.utils.Constants;
import com.techtalks.Okhttp3Client.utils.HttpClientException;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class RestClientImpl implements RestClient {

	@Autowired
	private OkHttp3RestClient okHttp3Client;

	public Response externalApiCall(HttpRequest request) {
		// switch case
		Headers requestHeaders = getHeaders(request);
		MediaType mediaType = getMediaType(request);
		String url = request.getUrl();
		// RequestBody body = RequestBody.create(mediaType,
		// request.getRequestBody().toString());
		Request requestObject = null;
		switch (request.getRequestMethod().toString()) {
		case Constants.HTTP_POST:
			RequestBody postRequestbody = RequestBody.create(mediaType, request.getRequestBody().toString());
			requestObject = new Request.Builder().url(url).post(postRequestbody).headers(requestHeaders).build();
			break;
		case Constants.HTTP_PUT:
			RequestBody putRequestbody = RequestBody.create(mediaType, request.getRequestBody().toString());
			requestObject = new Request.Builder().url(url).put(putRequestbody).headers(requestHeaders).build();
			break;
		case Constants.HTTP_GET:
			requestObject = new Request.Builder().url(url).headers(requestHeaders).build();
			break;
		case Constants.HTTP_HEAD:
			requestObject = new Request.Builder().url(url).head().build();
			break;
		case Constants.HTTP_DELETE:
			requestObject = new Request.Builder().url(url).delete().build();
			break;
		default:
			throw new HttpClientException("Not yet implemented");

		}
		return okHttp3Client.makeHttpCall(requestObject);

	}

	@Override
	public Map<String, String> executeExternalApi(HttpRequest request) {
		Map<String, String> map = new HashMap<>();
		try {
			Response response = externalApiCall(request);
			String stringResponse = response.body().string();
			map.put(Constants.RESPONSE_STR, stringResponse);
			map.put(Constants.STATUS_CODE, String.valueOf(response.code()));
			map.put(Constants.IS_SUCCESS, String.valueOf(response.isSuccessful()));
			return map;
		} catch (IOException e) {
			throw new HttpClientException(e.getMessage());
		} catch (Exception ex) {
			throw new HttpClientException(ex.getMessage());
		}
	}

	private Headers getHeaders(HttpRequest request) {
		if (request.getHeader() != null || !request.getHeader().isEmpty()) {
			Headers requestHeaders = Headers.of(request.getHeader());
			return requestHeaders;
		}
		return null;

	}

	private MediaType getMediaType(HttpRequest request) {
		MediaType mediaType = MediaType.parse(Constants.APPLICATION_JSON_CHARSET_UTF_8);
		String contentType = request.getHeader().get(Constants.CONTENT_TYPE);
		// default media type will be applicaiton/json
		if (null != contentType) {
			mediaType = MediaType.parse(contentType);
		}
		return mediaType;
	}

}
