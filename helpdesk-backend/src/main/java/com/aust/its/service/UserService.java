package com.aust.its.service;

import com.aust.its.entity.User;
import com.aust.its.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
