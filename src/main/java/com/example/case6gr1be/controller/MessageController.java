package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.Message;
import com.example.case6gr1be.service.MessageService;
import com.example.case6gr1be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @PostMapping("/mess")
    public boolean add(@RequestBody Message message) {
        messageService.save(message);
        return true;
    }

    @GetMapping("/mess")
    public ResponseEntity<Iterable<Message>> findAll() {
        return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/messRent/{id}")
    public ResponseEntity<Iterable<Message>> getAllMessByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(messageService.getAllMessByRenter(id), HttpStatus.OK);
    }
    @GetMapping("/messOneRent/{id}")
    public ResponseEntity<Iterable<Message>> getMessByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(messageService.getMessByRenter(id), HttpStatus.OK);
    }
    @GetMapping("/mess/{idPro}/{idRe}")
    public ResponseEntity<Iterable<Message>> getMess(@PathVariable Long idPro,@PathVariable Long idRe) {
        return new ResponseEntity<>(messageService.getMess(idPro,idRe), HttpStatus.OK);
    }
}
