package com.urlShortner.Url_Shortner.controllers;

import com.urlShortner.Url_Shortner.DTO.CreateNewUserRequest;
import com.urlShortner.Url_Shortner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> createNewUser(@RequestBody CreateNewUserRequest newUser) {
        if (!newUser.getUserName().isBlank() && !newUser.getPassword().isEmpty()) {
            userService.createNewUser(newUser);
            return new ResponseEntity<>(newUser.getUserName(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
