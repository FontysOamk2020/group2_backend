package com.hotspotted.server.service;

import com.hotspotted.server.dto.Auth0User;

public interface Auth0Service {
    Auth0User findUser(String accessToken);
}