package com.dejaraczo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "analytics")
public class Analytic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String datasource;

  @Column
  private String campaign;

  @Column
  private Date daily;

  @Column
  private Long clicks;

  @Column
  private Long impressions;


}
