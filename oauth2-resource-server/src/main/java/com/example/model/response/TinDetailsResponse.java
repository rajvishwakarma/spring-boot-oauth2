package com.example.model.response;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.model.TinDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TinDetailsResponse extends BaseResponse {

	private Collection<TinDetails> tinDetails;
	

	public TinDetailsResponse() {
	}

	public TinDetailsResponse(HttpStatus code, String message, Collection<TinDetails> tinDetails) {
		this.code = code;
		this.tinDetails = tinDetails;
		this.message = message;
	}

	
	public TinDetailsResponse(HttpStatus code, String message) {
		this.code = code;
		this.message = message;
	}
}