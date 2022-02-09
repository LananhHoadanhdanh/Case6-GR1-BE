package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.SerProvinder;
import com.example.case6gr1be.service.SerProvinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class SerProvinderController {
    @Autowired
    private SerProvinderService serProvinderService;

    @GetMapping("/SerProvinder")
    public ResponseEntity<Iterable<SerProvinder>> findAll() {
        return new ResponseEntity<>(serProvinderService.findAll(), HttpStatus.OK);
    }
}
