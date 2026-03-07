package com.urlShortner.Url_Shortner.services;

import com.urlShortner.Url_Shortner.DTO.CreateNewUserRequest;
import com.urlShortner.Url_Shortner.entity.User;
import com.urlShortner.Url_Shortner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createNewUser(CreateNewUserRequest newUser) {
        User user = new User();
        user.setUserName(newUser.getUserName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRoles(Collections.singleton("USER"));

        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
