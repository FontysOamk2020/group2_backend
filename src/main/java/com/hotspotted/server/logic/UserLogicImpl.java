package com.hotspotted.server.logic;

import com.hotspotted.server.entity.User;
import com.hotspotted.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogicImpl implements UserLogic {

    private final UserService userService;

    @Autowired
    public UserLogicImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User createOrUpdate(User user) {
        return userService.createOrUpdate(user);
    }
}
