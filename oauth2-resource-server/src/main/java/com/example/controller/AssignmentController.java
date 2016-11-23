package com.example.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.TinDetails;
import com.example.model.response.TinDetailsResponse;
import com.example.service.ScrapeDataService;

@RestController
public class AssignmentController {
	
	private Logger LOGGER = LoggerFactory.getLogger(AssignmentController.class);


	@Autowired
	ScrapeDataService scrapeDataService;

	/**
	 * Processes the Tin Details Request and Scrapes through the TIN Website
	 * @param tinNo
	 * @param model
	 * @return String (index.html) with appropriate data and messages
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "searchTIN", method = RequestMethod.POST)
	public ResponseEntity<TinDetailsResponse> index(@RequestParam("tinNo") String tinNo, Model model) throws Exception {
		if(! validTinNo(tinNo)){
			LOGGER.info("Invalid TIN ");
			
			return new ResponseEntity<TinDetailsResponse>(
					new TinDetailsResponse(HttpStatus.BAD_REQUEST, "Invalid TIN"),
					HttpStatus.BAD_REQUEST);
		}

		LOGGER.info("++ Valid TIN Request ++ TIN No "+tinNo);

		String url = "http://mahavat.gov.in/Tin_Search/TinSearch";

		Collection<TinDetails> tinDetails = scrapeDataService.fetchScrapedData(url, tinNo);

		if(null != tinDetails && tinDetails.size() > 0){
			LOGGER.info("TIN Details matched");
			
			return new ResponseEntity<TinDetailsResponse>(
					new TinDetailsResponse(HttpStatus.OK, "TIN Details matched", tinDetails),
					HttpStatus.OK);
		}
		else {
			LOGGER.info("No Results");
			
			return new ResponseEntity<TinDetailsResponse>(
					new TinDetailsResponse(HttpStatus.BAD_REQUEST, "No Results "),
					HttpStatus.BAD_REQUEST);
		}
	}

	private boolean validTinNo(String tinNo) {
		boolean validTin = false;

		if(null != tinNo && tinNo.trim().length() > 0){
			validTin = true;
		}
		return validTin;
	}

}