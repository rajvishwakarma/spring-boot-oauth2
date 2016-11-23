package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController {
	
	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Processes the Tin Details Request and Scrapes through the TIN Website
	 * @param tinNo
	 * @param model
	 * @return String (index.html) with appropriate data and messages
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity<String> index() throws Exception {
		
		String url = "tin:tinsecret@localhost:9000/identity/oauth/token?grant_type=password&client_id=tin&client_secret=abc123&redirect_uri=http://www.xyz.com&username=user&password=usersecret";
		
		Map<String, Object> requestBody = new HashMap<String, Object>();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
				requestHeaders);
		ResponseEntity<Map> response = null;
		try {
			response = restTemplate.
					exchange(url, 
							HttpMethod.POST, httpEntity,  Map.class);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}

}