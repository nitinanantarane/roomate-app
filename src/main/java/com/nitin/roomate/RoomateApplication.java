package com.nitin.roomate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;/*
													import org.springframework.security.core.Authentication;
													import org.springframework.security.core.context.SecurityContextHolder;
													import org.springframework.security.oauth2.core.AbstractOAuth2Token;*/
//import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RoomateApplication{

	public static void main(String[] args) {
		SpringApplication.run(RoomateApplication.class, args);
	}
	
	
	/*
	 * @Bean public RestTemplate restTemplate() { RestTemplate restTemplate = new
	 * RestTemplate();
	 * 
	 * restTemplate.getInterceptors().add((request, body, execution) -> {
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * if (authentication == null) { return execution.execute(request, body); }
	 * 
	 * if (! (authentication.getCredentials() instanceof AbstractOAuth2Token)) {
	 * return execution.execute(request, body); }
	 * 
	 * AbstractOAuth2Token authToken = (AbstractOAuth2Token)
	 * authentication.getCredentials();
	 * request.getHeaders().setBearerAuth(authToken.getTokenValue());
	 * 
	 * return execution.execute(request, body); });
	 * 
	 * return restTemplate; }
	 */
	
}
