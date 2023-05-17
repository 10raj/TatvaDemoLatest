//package com.example.Readers.config.interceptor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//@Slf4j
//public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
//	
//    private OAuth2AuthorizedClientManager manager;
//
//    private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);
//
//    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
//        this.manager = manager;
//    }
//
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//    	logger.info("Rest Template interceptor: Token :  {} ",body.toString());
//        String token = manager.authorize(OAuth2AuthorizeRequest
//        		.withClientRegistrationId("my-internal-client")
//        		.principal("internal").build()).getAccessToken()
//        		.getTokenValue();
//
//        logger.info("Rest Template interceptor: Token :  {} ",token);
//        request.getHeaders().add("Authorization","Bearer "+token);
//        return execution.execute(request,body);
//    }
//}