package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class CourierAPIClientConfig {

    @Value("${app.courier-api-name}")
    private String courierApiName;

    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalacendRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public CourierAPIClient courierAPIClient(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl(courierApiName)
                .requestFactory(generateClientRequestFactory())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(CourierAPIClient.class);
    }

    private ClientHttpRequestFactory generateClientRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofMillis(10));
        factory.setReadTimeout(Duration.ofMillis(200));
        return factory;
    }

}
