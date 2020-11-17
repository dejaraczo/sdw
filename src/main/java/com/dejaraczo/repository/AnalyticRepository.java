package com.dejaraczo.repository;

import com.dejaraczo.model.Analytic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticRepository extends JpaRepository<Analytic, Long>, JpaSpecificationExecutor<Analytic> {
}
