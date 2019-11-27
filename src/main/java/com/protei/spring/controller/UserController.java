package com.protei.spring.controller;

import com.protei.spring.model.User;
import com.protei.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping(path = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user, BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            throw new RuntimeException(String.valueOf(bindingResult));
        }

        Long newUserId = userService.addUser(user);

        logger.info(user.toString());

        responseMap.put("id", newUserId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserId).toUri();

        return ResponseEntity.created(location).body(responseMap);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) {
        logger.info("id : " + userId);
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}


