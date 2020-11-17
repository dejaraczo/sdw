package com.dejaraczo.model;

import lombok.Getter;

@Getter
public enum Metrics {
  clicks("totalClicks"), impressions("totalImpressions");

  private String totalName;

  Metrics(final String name) {
    totalName = name;
  }
}
