package com.ogmatech.pradeep.config;

import com.ogmatech.pradeep.model.DbtwoNote;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.ogmatech.pradeep.daotwo",
        entityManagerFactoryRef = "dbtwoEntityManagerFactory",
        transactionManagerRef= "dbtwoTransactionManager")
public class DbtwoDatasourceConfig {

    @Bean
    @ConfigurationProperties("mysql.datasource.dbtwo")
    public DataSourceProperties dbtwoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("mysql.datasource.dbtwo.configuration")
    public DataSource dbtwoDataSource() {
        return dbtwoDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "dbtwoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dbtwoEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dbtwoDataSource())
                .packages(DbtwoNote.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager dbtwoTransactionManager(
            final @Qualifier("dbtwoEntityManagerFactory") EntityManagerFactory dbtwoEntityManagerFactory) {
        return new JpaTransactionManager(dbtwoEntityManagerFactory);
    }

}
