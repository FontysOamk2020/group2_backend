package com.hotspotted.server.service;

import com.hotspotted.server.entity.HotSpot;
import com.hotspotted.server.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createOrUpdate(User user);
}
