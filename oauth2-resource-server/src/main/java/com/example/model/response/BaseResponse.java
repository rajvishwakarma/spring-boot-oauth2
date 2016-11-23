package com.example.model.response;

import org.springframework.http.HttpStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseResponse {

	public HttpStatus code;
	public String message;

	public BaseResponse(HttpStatus code, String message) {
		this.code = code;
		this.message = message;
	}
}