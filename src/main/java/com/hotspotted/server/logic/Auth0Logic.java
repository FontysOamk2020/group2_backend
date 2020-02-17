package com.hotspotted.server.logic;

import com.hotspotted.server.dto.Auth0User;

public interface Auth0Logic {
    Auth0User findUser(String accessToken);
}
