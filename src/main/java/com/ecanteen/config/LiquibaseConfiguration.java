package com.ecanteen.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
@ConditionalOnProperty(value = "spring.liquibase.enabled",havingValue = "true",matchIfMissing = true)
public class LiquibaseConfiguration {

    private  final DataSource dataSource;

    private final LiquibaseProperties liquibaseProperties;



    public LiquibaseConfiguration(DataSource dataSource, LiquibaseProperties liquibaseProperties ) {
        this.dataSource = dataSource;
        this.liquibaseProperties = liquibaseProperties;

    }

    @Bean
    public SpringLiquibase liquibase()   {

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());

        return liquibase;


    }
}
