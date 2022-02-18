package com.example.case6gr1be.controller;

import com.example.case6gr1be.model.Order;
import com.example.case6gr1be.model.Report;
import com.example.case6gr1be.model.ReportStatus;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.service.ReportService;
import com.example.case6gr1be.service.ReportStatusService;
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
    @Autowired
    private ReportStatusService reportStatusService;

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
    public ResponseEntity<Report> approve(@PathVariable Long id) {
        Report report = reportService.findById(id).get();
        ReportStatus status = reportStatusService.findById(2L).get();
        report.setStatus(status);
        report.setId(id);
        reportService.save(report);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("users/{id}/reportsByRenter")
    public ResponseEntity<Iterable<Report>> allReportByRenter(@PathVariable Long id) {
        return new ResponseEntity<>(reportService.findAllReportByRenter(id), HttpStatus.OK);
    }

    @GetMapping("users/{id}/reportsByProvider")
    public ResponseEntity<Iterable<Report>> allReportByProvider(@PathVariable Long id) {
        return new ResponseEntity<>(reportService.findAllReportByProvider(id), HttpStatus.OK);
    }

    @GetMapping("orders/{id}/reports")
    public ResponseEntity<Iterable<Report>> findAllByOrder(@PathVariable Long id) {
        return new ResponseEntity<>(reportService.findAllByOrder(id), HttpStatus.OK);
    }
}
