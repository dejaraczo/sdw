package com.dejaraczo.service;

import com.dejaraczo.config.ModelMapper;
import com.dejaraczo.dto.AnalyticDto;
import com.dejaraczo.dto.MetricsResultsDto;
import com.dejaraczo.model.Analytic;
import com.dejaraczo.model.Metrics;
import com.dejaraczo.repository.AnalyticRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticService {

  private final AnalyticRepository analyticRepository;
  private final ModelMapper modelMapper;

  public AnalyticService(final AnalyticRepository analyticRepository, final ModelMapper modelMapper) {
    this.analyticRepository = analyticRepository;
    this.modelMapper = modelMapper;
  }

  public void addAnalytic(final AnalyticDto analyticDto) {
    analyticRepository.save(modelMapper.map(analyticDto, Analytic.class));
  }

  @Cacheable("analytics")
  public List<Analytic> getAllAnalytics() {
    return analyticRepository.findAll();
  }

  public List<AnalyticDto> getAllAnalyticsDto() {
    return getAllAnalytics().stream()
        .map(a -> modelMapper.map(a, AnalyticDto.class))
        .collect(Collectors.toList());
  }

  public MetricsResultsDto getTotalClicksByDatasource(
      final List<Metrics> metricList, final String datasource, final String campaign,
      final Date fromDate, final Date toDate
  ) {
//    String metrics = metricList.stream().map(v -> "sum(" + v + ") as " + v.getTotalName()).collect(Collectors.joining(", "));
//    MetricsDto metricsDto = analyticRepository.findMetricsByDatasource(metrics, datasource, campaign, fromDate, toDate);

    List<Analytic> analyticList = getAllAnalytics().stream()
        .filter(a -> datasource == null || datasource.equalsIgnoreCase(a.getDatasource()))
        .filter(a -> campaign == null || campaign.equalsIgnoreCase(a.getCampaign()))
        .filter(a -> fromDate == null || a.getDaily().after(fromDate))
        .filter(a -> toDate == null || a.getDaily().before(toDate))
        .collect(Collectors.toList());

    long totalClicks = 0L;
    if (metricList.contains(Metrics.clicks)) {
      totalClicks = analyticList.stream().map(Analytic::getClicks).mapToLong(Long::longValue).sum();
    }

    long totalImpressions = 0L;
    if (metricList.contains(Metrics.impressions)) {
      totalImpressions = analyticList.stream().map(Analytic::getImpressions).mapToLong(Long::longValue).sum();
    }

    return MetricsResultsDto.builder()
        .campaign(campaign)
        .datasource(datasource)
        .dateFrom(fromDate)
        .dateto(toDate)
        .totalClicks(totalClicks)
        .totalImpressions(totalImpressions)
        .build();
  }

}
