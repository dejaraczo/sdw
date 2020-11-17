package com.dejaraczo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsResultsDto {
  private Long totalClicks;
  private Long totalImpressions;
  private String datasource;
  private String campaign ;
  private Date dateFrom;
  private Date dateto;
}
