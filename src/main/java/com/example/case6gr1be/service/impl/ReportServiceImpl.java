package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.Report;
import com.example.case6gr1be.model.User;
import com.example.case6gr1be.repository.ReportRepository;
import com.example.case6gr1be.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    @Override
    public void save(Report report) {
        reportRepository.save(report);
    }

    @Override
    public void approveReport(Long id) {
        reportRepository.approveReport(id);
    }

    @Override
    public Iterable<Report> findAllReportByProvider(Long id) {
        return reportRepository.findAllReportByProvider(id);
    }

    @Override
    public Iterable<Report> findAllReportByRenter(Long id) {
        return reportRepository.findAllReportByRenter(id);
    }
}
