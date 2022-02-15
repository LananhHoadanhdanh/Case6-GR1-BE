package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.StatusUser;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.service.StatusUserService;
import com.example.case6gr1be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.Optional;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private StatusUserService statusUserService;

    @PutMapping("/users/{id}/lockAccount")
    public ResponseEntity<User> lockAccount(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();

        Optional<StatusUser> statusUser = statusUserService.findById(3L);
        StatusUser status = statusUser.get();

        user.setStatus(status);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/updateVipAccount")
    public ResponseEntity<User>updateVipAccount(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();

        Optional<StatusUser> statusUser = statusUserService.findById(4L);
        StatusUser status = statusUser.get();

        user.setStatus(status);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/browseAccount")
    public ResponseEntity<User>browseAccounts(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();

        Optional<StatusUser> statusUser = statusUserService.findById(2L);
        StatusUser status = statusUser.get();

        user.setStatus(status);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/pauseAccount")
    public ResponseEntity<User>pauseAccounts(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        User user = userOptional.get();

        Optional<StatusUser> statusUser = statusUserService.findById(5L);
        StatusUser status = statusUser.get();

        user.setStatus(status);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
