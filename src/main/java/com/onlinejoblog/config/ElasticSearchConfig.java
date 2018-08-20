package com.onlinejoblog.config;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by pankaj on 08,2018
 */
@Configuration
public class ElasticSearchConfig {

        @Value("${es.host}")
        private String host;

        @Value("${es.port}")
        private Integer post;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
       /* credentialsProvider.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials(username, password));
*/
        RestClientBuilder builder = RestClient.builder(new HttpHost(host, post));
        return new RestHighLevelClient(builder);
    }
}
