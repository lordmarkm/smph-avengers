package com.smph.ar;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mynt.core.dto.BaseInfo;
import com.mynt.core.jpa.model.BaseEntity;
import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

@Configuration
@EnableJpaRepositories(repositoryImplementationPostfix = "CustomImpl")
public class PersistenceConfig {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(250);
        return executor;
    }

    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();

        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BaseEntity.class, BaseInfo.class)
                        .fields("createdDate", "createdDate", copyByReference())
                        .fields("updatedDate", "updatedDate", copyByReference());
            }
        });

        return mapper;
    }


}
