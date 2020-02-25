package com.ogmatech.pradeep.config;

import com.ogmatech.pradeep.model.DboneNote;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ogmatech.pradeep.daoone",
        entityManagerFactoryRef = "dboneEntityManagerFactory",
        transactionManagerRef= "dboneTransactionManager")
public class DboneDatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("mysql.datasource.dbone")
    public DataSourceProperties dboneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("mysql.datasource.dbone.configuration")
    public DataSource dboneDataSource() {
        return dboneDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "dboneEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dboneEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dboneDataSource())
                .packages(DboneNote.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager dboneTransactionManager(
            final @Qualifier("dboneEntityManagerFactory") EntityManagerFactory dboneEntityManagerFactory) {
        return new JpaTransactionManager(dboneEntityManagerFactory);
    }

}


/*

package com.ogmatech.pradeep.config;

import com.ogmatech.pradeep.model.DboneNote;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ogmatech.pradeep.dao",
        entityManagerFactoryRef = "dboneEntityManagerFactory",
        transactionManagerRef= "dboneTransactionManager")
public class DboneDatasourceConfig {

    @Primary
    @Bean(name = "dboneDataSource")
    @ConfigurationProperties(prefix="mysql.datasource.dbone")
    public DataSource dboneDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dboneEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dboneEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("dboneDataSource") DataSource ds) {
        return builder
                .dataSource(ds)
                .packages("com.ogmatech.pradeep.model")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager dboneTransactionManager(
            final @Qualifier("dboneEntityManagerFactory") EntityManagerFactory dboneEntityManagerFactory) {
        return new JpaTransactionManager(dboneEntityManagerFactory);
    }

}

 */

