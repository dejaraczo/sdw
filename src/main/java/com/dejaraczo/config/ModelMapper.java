package com.dejaraczo.config;

import com.dejaraczo.dto.AnalyticDto;
import com.dejaraczo.model.Analytic;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Component
@Slf4j
public class ModelMapper extends DozerBeanMapper {

  @PostConstruct
  public void setup() {
    BeanMappingBuilder builder = new BeanMappingBuilder() {

      @Override
      protected void configure() {
        mapping(AnalyticDto.class, Analytic.class)
            .fields(
                "daily",
                "daily",
                FieldsMappingOptions.customConverter(StringToDateConverter.class)
            );
      }
    };
    setCustomConverters(Collections.singletonList(new StringToDateConverter()));
    addMapping(builder);
  }

  public static class StringToDateConverter extends DozerConverter<String, Date> {

    //Date of format 11/13/19
    private final static SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yy");

    StringToDateConverter() {
      super(String.class, Date.class);
    }

    @Override
    public Date convertTo(String source, Date destination) {
      try {
        return SDF.parse(source);
      } catch (ParseException e) {
        log.error(e.getMessage());
        return null;
      }
    }

    @Override
    public String convertFrom(Date source, String destination) {
      return SDF.format(source);
    }
  }
}
