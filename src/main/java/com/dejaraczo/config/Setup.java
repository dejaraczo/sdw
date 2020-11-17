package com.dejaraczo.config;

import com.dejaraczo.service.AnalyticService;
import com.dejaraczo.service.CsvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Setup {

  private final CsvService csvService;
  private final AnalyticService analyticService;

  public Setup(final CsvService csvService, final AnalyticService analyticService) {
    this.csvService = csvService;
    this.analyticService = analyticService;
  }

  @PostConstruct
  private void setupData() {
    csvService.getAnalytics().forEach(analyticService::addAnalytic);
  }
}
