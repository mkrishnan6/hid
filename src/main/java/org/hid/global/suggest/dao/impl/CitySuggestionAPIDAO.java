package org.hid.global.suggest.dao.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hid.global.suggest.config.ElasticSearchConfig;
import org.hid.global.suggest.dao.iface.ICitySuggestionAPIDAO;
import org.hid.global.suggest.exceptions.CitySuggestionAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("citySuggestionAPIDAO")
public class CitySuggestionAPIDAO implements ICitySuggestionAPIDAO {
	
	private final Logger logger = LoggerFactory
            .getLogger(CitySuggestionAPIDAO.class);
	
	public List<String> suggestCitiesByPrefix(String start,String atmost) throws CitySuggestionAPIException{
		
		logger.info("inside prefix search result");
        SearchRequest searchRequest = new SearchRequest("cities");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String[] includeFields = new String[] {"name"};
        String[] excludeFields = new String[] {};
        searchSourceBuilder.query(QueryBuilders.prefixQuery(start, atmost)).fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
		try {
			searchResponse = ElasticSearchConfig.getClient().search(searchRequest,RequestOptions.DEFAULT);
			logger.info("response{}", searchResponse);
	        return Arrays.stream(searchResponse.getHits().getHits())
	                .map(SearchHit::getSourceAsMap).map(
	                        Map::values).flatMap(Collection::stream)
	                .map(Object::toString)
	                .collect(Collectors.toList());
		} catch (IOException ioException) {
			logger.info("IO Exception occurred while fetching suggestions");
			throw new CitySuggestionAPIException("IO Exception occurred while fetching suggestions", ioException);
		} catch (Exception exception) {
			logger.info("Exception occurred while fetching suggestions");
			throw new CitySuggestionAPIException("Exception occurred while fetching suggestions", exception);
		}
		
	}

}
