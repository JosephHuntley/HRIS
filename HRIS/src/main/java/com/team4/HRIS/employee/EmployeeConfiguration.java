package com.team4.HRIS.employee;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
// Author - Joseph Huntley
// Team 4
@Configuration
public class EmployeeConfiguration {
    @Bean
    public static DataSource getDataSource()
    {
        //TODO: Gather information from application.properties instead
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/hris");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("admin");
        return dataSourceBuilder.build();
    }
}
