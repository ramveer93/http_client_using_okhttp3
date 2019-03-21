package com.techtalks.Okhttp3Client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.techtalks.Okhttp3Client.utils.ResponseUtils;


public class ResponseUtilsTest {
	
	
	@InjectMocks
	private ResponseUtils responseUtils;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void responseUtilsTestBuild() {
		ResponseUtils actual = this.responseUtils.build(200, "test user message", "test developer message");
		Assert.assertEquals(200, actual.getStatusCode());
	}

}
