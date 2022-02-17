package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.Report;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public ResponseEntity<Iterable<Report>> findAll() {
        return new ResponseEntity<>(reportService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/reports")
    public ResponseEntity<Report> save(@RequestBody Report report) {
        report.setDate(new Date());
        reportService.save(report);
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<Optional<Report>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(reportService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/reports/{id}/approve")
    public ResponseEntity<Long> approve(@PathVariable Long id) {
        reportService.approveReport(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("users/{id}/reportsByRenter")
    public ResponseEntity<Iterable<Report>> allReportByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(reportService.findAllReportByRenter(id), HttpStatus.OK);
    }

    @GetMapping("users/{id}/reportsByProvider")
    public ResponseEntity<Iterable<Report>> allReportByProvider(@PathVariable Long id) {
        return new ResponseEntity<>(reportService.findAllReportByProvider(id), HttpStatus.OK);
    }
}
