package com.aust.its.controller;

import com.aust.its.dto.LoginPayload;
import com.aust.its.entity.User;
import com.aust.its.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginPayload loginPayload) {

        logger.info("login payload is : {}", loginPayload);

        User user = userRepository.findByUsernameAndPassword(loginPayload.username(), loginPayload.password())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        logger.info("Currently login user is : {}", user);
        return user;
    }
}