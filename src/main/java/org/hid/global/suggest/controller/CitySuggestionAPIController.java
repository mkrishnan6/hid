package org.hid.global.suggest.controller;

import java.util.List;

import org.hid.global.suggest.exceptions.CitySuggestionAPIException;
import org.hid.global.suggest.service.iface.ICitySuggestionAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
public class CitySuggestionAPIController {
	
	@Autowired
	private ICitySuggestionAPIService citySuggestionAPIService;
	
	private final Logger logger = LoggerFactory
            .getLogger(CitySuggestionAPIController.class);
	
	@RequestMapping(path="/suggest_cities",method = RequestMethod.GET)
	@ResponseBody
    public List<String> suggestCitiesByPrefix(@RequestParam String start, @RequestParam String atmost) {
        List<String> suggestCities = null;
		try {
			suggestCities = citySuggestionAPIService.suggestCitiesByPrefix("name.keywordstring", start);
		} catch (CitySuggestionAPIException e) {
			logger.info("Exception Occurred while fetching cities suggestion list");
		}
		
        return suggestCities;

    }
}
