package com.example.case6gr1be.service.impl;

import com.example.case6gr1be.model.ReportStatus;
import com.example.case6gr1be.repository.ReportStatusRepository;
import com.example.case6gr1be.service.ReportStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepostStatusServiceImpl implements ReportStatusService {
    @Autowired
    private ReportStatusRepository reportStatusRepository;

    @Override
    public Iterable<ReportStatus> findAll() {
        return reportStatusRepository.findAll();
    }

    @Override
    public Optional<ReportStatus> findById(Long id) {
        return reportStatusRepository.findById(id);
    }

    @Override
    public void save(ReportStatus reportStatus) {

    }
}
