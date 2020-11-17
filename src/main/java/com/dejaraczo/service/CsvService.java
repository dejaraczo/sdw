package com.dejaraczo.service;

import com.dejaraczo.dto.AnalyticDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CsvService {

  private final static String ANALYTIC_FILE = "PIxSyyrIKFORrCXfMYqZBI.csv";
  private final CsvDataLoader csvDataLoader;

  public CsvService(final CsvDataLoader csvDataLoader) {
    this.csvDataLoader = csvDataLoader;
  }

  public List<AnalyticDto> getAnalytics() {
    return csvDataLoader.loadObjectList(AnalyticDto.class, ANALYTIC_FILE);
  }
}
