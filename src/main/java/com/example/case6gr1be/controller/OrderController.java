package com.example.case6gr1be.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {

}
