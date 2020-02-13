package com.hotspotted.server.service;

import com.hotspotted.server.entity.User;
import com.hotspotted.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User createOrUpdate(User user) {
        Optional<User> foundUser = findById(user.getId());

        if(foundUser.isPresent()) {
            foundUser.get().setName(user.getName());
            return userRepository.save(foundUser.get());
        } else {
            return userRepository.save(user);
        }
    }
}
