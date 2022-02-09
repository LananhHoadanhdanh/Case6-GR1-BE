package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.SerProvided;
import com.example.case6gr1be.service.SerProvidedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class SerProvidedController {
    @Autowired
    private SerProvidedService serProvinderService;

    @GetMapping("/service")
    public ResponseEntity<Iterable<SerProvided>> findAll() {
        return new ResponseEntity<>(serProvinderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/actService/{id}")
    public ResponseEntity<int[]> actService(@PathVariable Long id,int idService[]) {
        for (int i = 0; i < idService.length; i++) {
            serProvinderService.a
        }

        return new ResponseEntity<>(idService, HttpStatus.OK);
    }
}
