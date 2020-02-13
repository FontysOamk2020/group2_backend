package com.hotspotted.server.logic;

import com.hotspotted.server.entity.User;

public interface UserLogic {
    User createOrUpdate(User user);
}
