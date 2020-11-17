package com.dejaraczo.controller;

import com.dejaraczo.dto.AnalyticDto;
import com.dejaraczo.dto.MetricsResultsDto;
import com.dejaraczo.model.Metrics;
import com.dejaraczo.service.AnalyticService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static com.dejaraczo.controller.ControllerConstants.API;

@RestController
@RequestMapping(API)
public class AnalyticController {

  private final AnalyticService analyticService;

  public AnalyticController(final AnalyticService analyticService) {
    this.analyticService = analyticService;
  }

  @GetMapping(value = "/getAllAnalytics")
  public List<AnalyticDto> getAllAnalytics() {
    return analyticService.getAllAnalyticsDto();
  }

  @GetMapping(value = "/analyticsResults")
  public MetricsResultsDto getTotalClicksByDatasource(
      @RequestParam List<Metrics> metricsList,
      @RequestParam(required = false) @ApiParam(example = "Facebook Ads") String datasource,
      @RequestParam(required = false) @ApiParam(example = "SN_Brand") String campaign,
      @RequestParam(required = false) @ApiParam(example = "11/23/19") Date fromDate,
      @RequestParam(required = false) @ApiParam(example = "11/26/19") Date toDate
  ) {
    return analyticService.getTotalClicksByDatasource(metricsList, datasource, campaign, fromDate, toDate);
  }
}
