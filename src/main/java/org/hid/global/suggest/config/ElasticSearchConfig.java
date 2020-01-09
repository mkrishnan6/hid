package org.hid.global.suggest.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = { "org.hid.global" })
public class ElasticSearchConfig {
	
    private static RestClient restClient =
            RestClient.builder(new HttpHost("localhost", 9200, "http")).build();

    private static RestHighLevelClient client =
            new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));


    public static RestHighLevelClient getClient() {
        return client;
    }

    public static void close() throws Exception {
        restClient.close();
    }

}
