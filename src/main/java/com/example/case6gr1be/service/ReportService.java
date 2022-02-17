package com.example.case6gr1be.service;

import com.example.case6gr1be.model.Report;
import com.example.case6gr1be.model.User;
import org.springframework.data.repository.query.Param;

public interface ReportService extends GeneralService<Report> {
    void approveReport(Long id);
    Iterable<Report> findAllReportByProvider(Long id);
    Iterable<Report> findAllReportByRenter(Long id);
}
