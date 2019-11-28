package com.protei.spring.controller;

import com.protei.spring.exception.FieldContentException;
import com.protei.spring.model.User;
import com.protei.spring.model.UserStatus;
import com.protei.spring.service.UserService;
import com.protei.spring.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.protei.spring.model.UserStatus.Status.ONLINE;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStatusService userStatusService;

    private Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping(path = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new RuntimeException(String.valueOf(bindingResult));
        }

        Long newUserId = userService.addUser(user);
        userStatusService.setStatus(new UserStatus(newUserId, ONLINE));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("id", newUserId);

        return ResponseEntity.ok().body(responseMap);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) {
        logger.info("id : " + userId);
        return ResponseEntity.ok(userService.getUserInfoById(userId));
    }

    @PostMapping(path = "/{id}/setStatus")
    public ResponseEntity<?> setStatus(@PathVariable("id") Long userId,
                                       @RequestBody UserStatus userStatus,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new FieldContentException(bindingResult);
        }

        userStatus.setId(userId);

        logger.info("id : " + userId);
        logger.info("userStatus : " + userStatus.toString());

        return ResponseEntity.ok().body(userStatusService.setStatus(userStatus));
    }
}


