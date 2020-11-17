package com.dejaraczo.repository;

import com.dejaraczo.dto.MetricsDto;
import com.dejaraczo.model.Analytic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AnalyticRepository extends JpaRepository<Analytic, Long>, JpaSpecificationExecutor<Analytic> {

  @Query(value = "SELECT :metrics FROM ANALYTICS where datasource = :datasource and campaign = :campaign " +
      "and daily between :fromDate and :toDate", nativeQuery = true)
  MetricsDto findMetricsByDatasource(String metrics, String datasource, String campaign, Date fromDate, Date toDate);
}
