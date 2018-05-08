package com.smph.ar;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mynt.core.dto.BaseInfo;
import com.mynt.core.jpa.model.BaseEntity;
import com.smph.ar.customer.dto.PromoPointsInfo;
import com.smph.ar.customer.model.PromoPoints;
import static org.dozer.loader.api.FieldsMappingOptions.oneWay;

@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();

        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BaseEntity.class, BaseInfo.class)
                        .fields("createdDate", "createdDate", copyByReference())
                        .fields("updatedDate", "updatedDate", copyByReference());

                mapping(PromoPoints.class, PromoPointsInfo.class)
                        .fields("customer.code", "customerCode", oneWay());
            }
        });

        return mapper;
    }

}
