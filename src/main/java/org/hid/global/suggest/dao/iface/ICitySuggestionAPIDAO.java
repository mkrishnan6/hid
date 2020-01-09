package org.hid.global.suggest.dao.iface;

import java.util.List;

import org.hid.global.suggest.exceptions.CitySuggestionAPIException;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitySuggestionAPIDAO {
	
	public List<String> suggestCitiesByPrefix(String start,String atmost) throws CitySuggestionAPIException;

}
