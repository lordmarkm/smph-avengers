package com.smph.ar;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author mbmartinez, Jul 26, 2017
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("com.smph.ar.customer.resource"),
                        RequestHandlerSelectors.basePackage("com.smph.ar.coupon.resource")))
                .paths(PathSelectors.any())
                .build();
    }

}