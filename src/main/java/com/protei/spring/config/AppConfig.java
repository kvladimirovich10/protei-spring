package com.protei.spring.config;


import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;



@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("com.protei.spring.dao"), @ComponentScan("com.protei.spring.service") })
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("com.protei.spring.model");
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.pass"));

        return dataSource;
    }


    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(environment.getProperty("hibernate.hbm2ddl.auto")));
        properties.put("hibernate.dialect", Objects.requireNonNull(environment.getProperty("hibernate.dialect")));
        properties.put("hibernate.globally__quoted__identifiers", "true");

        return properties;
    }
}
