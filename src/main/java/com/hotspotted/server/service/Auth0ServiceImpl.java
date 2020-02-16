package com.hotspotted.server.service;

import com.hotspotted.server.dto.Auth0User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class Auth0ServiceImpl implements Auth0Service {
    private final RestTemplate restTemplate;

    @Value("${OATH2_DOMAIN}")
    private String auth0BaseUrl;

    @Autowired
    public Auth0ServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Auth0User findUser(String accessToken) {
        return restTemplate.exchange(auth0BaseUrl + "/userinfo", HttpMethod.GET, new HttpEntity<>(getRequestHeaders(accessToken)), Auth0User.class).getBody();
    }

    private HttpHeaders getRequestHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + accessToken);

        return headers;
    }
}
