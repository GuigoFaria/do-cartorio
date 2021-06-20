package com.docartorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableSpringDataWebSupport
public class DoCartorioApplication {
	@Bean
	public WebClient webClientCartorio (WebClient.Builder builder){
		return builder.
				baseUrl("http://localhost:8080/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

	}

	@Bean
	public WebClient webClientCertidao (WebClient.Builder builder){
		return builder.
				baseUrl("https://docketdesafiobackend.herokuapp.com/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

	}
	public static void main(String[] args) {
		SpringApplication.run(DoCartorioApplication.class, args);
	}

}
