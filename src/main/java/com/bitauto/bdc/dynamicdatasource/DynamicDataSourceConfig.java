package com.bitauto.bdc.dynamicdatasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiyongxu on 2017/11/30.
 */
@Configuration
public class DynamicDataSourceConfig {
    @Bean(name = "monitorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.monitor")
    public DataSource monitorDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "hiveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hive")
    public DataSource hiveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "oozieDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oozie")
    public DataSource oozieDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDatasource datasource(@Qualifier("monitorDataSource") DataSource monitorDataSource, @Qualifier("hiveDataSource") DataSource hiveDataSource, @Qualifier("oozieDataSource") DataSource oozieDataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.MONITOR, monitorDataSource);
        targetDataSources.put(DataSourceNames.HIVE, hiveDataSource);
        targetDataSources.put(DataSourceNames.OOZIE, oozieDataSource);
        return new DynamicDatasource(monitorDataSource, targetDataSources);
    }

}
