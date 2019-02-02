package com.gildedrose.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.gildedrose.repository")
public class ElasticsearchConfig {

        @Value("${elasticsearch.host}")
        private String EsHost;

        @Value("${elasticsearch.clustername}")
        private String EsClusterName;

        @Bean
        public Client client() throws Exception {

            Settings esSettings = Settings.builder()
                    .put("cluster.name", EsClusterName)
                    .put("path.home", EsHost)
                    .build();

            //https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
            return TransportClient.builder()
                    .settings(esSettings)
                    .build()
                    .addTransportAddress(
                            new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
        }

        @Bean
        public ElasticsearchOperations elasticsearchTemplate() throws Exception {
            return new ElasticsearchTemplate(client());
        }

}
