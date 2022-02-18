package com.example.case6gr1be.repository;

import com.example.case6gr1be.model.Report;
import com.example.case6gr1be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = "UPDATE report SET report.status_id = 2 WHERE report.id = :id", nativeQuery = true)
    void approveReport(@Param("id") Long id);

    @Query("select r from Report r where r.order.id = :id and r.status.id = 2 order by r.id desc")
    Iterable<Report> findAllByOrder(@Param("id") Long id);

    @Query(value = "select report.id, report.content, report.order_id, report.status_id, report.date from report\n" +
            "join `order_table` o on o.id = report.order_id\n" +
            "join user_table u on u.id = provider_id where provider_id = :id and report.status_id = 2 order by report.id desc", nativeQuery = true)
    Iterable<Report> findAllReportByProvider(@Param("id") Long id);

    @Query(value = "select report.id, report.content, report.order_id, report.status_id, report.date from report\n" +
            "join `order_table` o on o.id = report.order_id\n" +
            "join user_table u on u.id = renter_id where renter_id = :id and report.status_id = 2 order by report.id desc", nativeQuery = true)
    Iterable<Report> findAllReportByRenter(@Param("id") Long id);
}
