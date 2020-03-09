package com.hotspotted.server.logic;

import com.hotspotted.server.dto.Auth0User;
import com.hotspotted.server.service.Auth0Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Auth0LogicImpl implements Auth0Logic {
    private final Auth0Service auth0Service;

    @Autowired
    public Auth0LogicImpl(Auth0Service auth0Service) {
        this.auth0Service = auth0Service;
    }

    @Override
    public Auth0User findUser(String accessToken) {
        return auth0Service.findUser(accessToken);
    }
}
