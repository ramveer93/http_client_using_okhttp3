package com.techtalks.Okhttp3Client.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techtalks.Okhttp3Client.httpRequest.HttpRequest;
import com.techtalks.Okhttp3Client.service.HttpCallsUsingOkhttp3ApplicationService;
import com.techtalks.Okhttp3Client.utils.Constants;
import com.techtalks.Okhttp3Client.utils.HttpClientException;
import com.techtalks.Okhttp3Client.utils.ResponseUtils;

@RestController
@RequestMapping("restclient")
public class HttpCallsUsingOkhttp3ApplicationController {

	@Autowired
	private ResponseUtils responseUtils;
	@Autowired
	private HttpCallsUsingOkhttp3ApplicationService service;

	@RequestMapping(value = "/execute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> executeHttpCall(@RequestBody HttpRequest request) {
		if (request.isValid()) {
			System.out.println(request.toString());
			try {
				Map<String, String> resultMap = this.service.executeHttpRequest(request);
				String response = resultMap.get(Constants.RESPONSE_STR);
				int statusCode = Integer.parseInt(resultMap.get(Constants.STATUS_CODE));
				boolean isSuccess = Boolean.parseBoolean(resultMap.get(Constants.IS_SUCCESS));
				if (isSuccess) {
					return new ResponseEntity<Object>(response,HttpStatus.OK);
				}
				return new ResponseEntity<Object>(this.responseUtils.build(statusCode, response, response),
						HttpStatus.INTERNAL_SERVER_ERROR);

			} catch (HttpClientException ex) {
				return new ResponseEntity<Object>(this.responseUtils
						.build(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<Object>(this.responseUtils.build(HttpStatus.BAD_REQUEST.value(),
					"Validation failed, Pass mandetory input", "Validation failed, Pass mandetory input"),
					HttpStatus.BAD_REQUEST);
		}

	}

}
