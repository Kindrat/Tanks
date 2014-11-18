package com.github.kindrat.programmerwars.tanks.server.context;

import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.github.kindrat.programmerwars.tanks.server.persistence.repository"})
@EnableTransactionManagement
public class PersistenceContext {

    @Autowired
    private Config config;

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan("com.github.kindrat.programmerwars.tanks.server.persistence");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        em.setPersistenceUnitName("persistenceUnit");
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getString("db.datasource.driver"));
        dataSource.setUrl(config.getString("db.datasource.url"));
        dataSource.setUsername(config.getString(("db.datasource.username")));
        dataSource.setPassword(config.getString("db.datasource.password"));
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean(name = "exceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.jdbc.driver", config.getString("db.datasource.driver"));
        properties.setProperty("javax.persistence.jdbc.url", config.getString("db.datasource.url"));
        properties.setProperty("javax.persistence.jdbc.user", config.getString(("db.datasource.username")));
        properties.setProperty("javax.persistence.jdbc.password", config.getString("db.datasource.password"));
        properties.setProperty("hibernate.dialect", config.getString("db.hibernate.dialect"));
        properties.setProperty("hibernate.c3p0.max_size", "100");
        properties.setProperty("hibernate.c3p0.preferredTestQuery", "select 1");
        properties.setProperty("hibernate.c3p0.testConnectionOnCheckout", "true");
        return properties;
    }
}
