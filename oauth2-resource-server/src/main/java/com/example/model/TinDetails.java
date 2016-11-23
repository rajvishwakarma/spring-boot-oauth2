package com.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TinDetails {

	private String oldRcNo;
	private String tinNo;
	private String dealerName;
	private String status;


	public TinDetails(String oldRcNo, String tinNo, String dealerName, String status){
		this.oldRcNo = oldRcNo;
		this.tinNo = tinNo;
		this.dealerName = dealerName;
		this.status = status;
	}

}