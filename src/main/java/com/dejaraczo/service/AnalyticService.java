package com.dejaraczo.service;

import com.dejaraczo.config.ModelMapper;
import com.dejaraczo.dto.AnalyticDto;
import com.dejaraczo.model.Analytic;
import com.dejaraczo.repository.AnalyticRepository;
import org.springframework.stereotype.Service;

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
}
