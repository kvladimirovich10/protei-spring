package com.protei.spring.config;

import com.protei.spring.service.UserService;
import com.protei.spring.service.UserStatusService;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

import static com.protei.spring.serviceImpl.UserServiceImpl.getUserServiceImpl;
import static com.protei.spring.serviceImpl.UserStatusServiceImpl.getUserStatusServiceImpl;


@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories("com.protei.spring.repository")
@ComponentScan(basePackages = "com.protei.spring")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public UserService userService() {
        return getUserServiceImpl();
    }

    @Bean
    public UserStatusService userStatusService() {
        return getUserStatusServiceImpl();
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("Scheduler");
        return threadPoolTaskScheduler;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(getDataSource());
        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        bean.setPackagesToScan("com.protei.spring.model");
        bean.setJpaProperties(getHibernateProperties());
        return bean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setPackagesToScan("com.protei.spring.model");
        sessionFactory.setDataSource(getDataSource());
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
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties getHibernateProperties() {

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(environment.getProperty("hibernate.hbm2ddl.auto")));
        properties.put("hibernate.dialect", Objects.requireNonNull(environment.getProperty("hibernate.dialect")));
        properties.put("hibernate.globally__quoted__identifiers", "true");

        return properties;
    }
}
