package org.hid.global.suggest.service.iface;

import java.util.List;

import org.hid.global.suggest.exceptions.CitySuggestionAPIException;
import org.springframework.stereotype.Service;

@Service
public interface ICitySuggestionAPIService {
	public List<String> suggestCitiesByPrefix(String start,String atmost) throws CitySuggestionAPIException;
}
