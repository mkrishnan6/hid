package org.hid.global.suggest.service.impl;

import java.util.List;

import org.hid.global.suggest.dao.iface.ICitySuggestionAPIDAO;
import org.hid.global.suggest.exceptions.CitySuggestionAPIException;
import org.hid.global.suggest.service.iface.ICitySuggestionAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("citySuggestionAPIService")
public class CitySuggestionAPIService implements ICitySuggestionAPIService {
	
	@Autowired
	private ICitySuggestionAPIDAO citySuggestionAPIDAO;
	
	public List<String> suggestCitiesByPrefix(String start,String atmost) throws CitySuggestionAPIException{
		
		return citySuggestionAPIDAO.suggestCitiesByPrefix(start, atmost);
	}

}
