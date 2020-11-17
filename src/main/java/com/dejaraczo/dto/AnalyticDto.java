package com.dejaraczo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticDto {
  private String datasource;
  private String campaign;
  private String daily;
  private Integer clicks;
  private Integer impressions;
}
