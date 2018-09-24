package com.gmail.sasha.myproject.dao.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.cfg.Environment;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.gmail.sasha.myproject.dao"})
public class HibernateConfig {

    private static final Logger logger = LogManager.getLogger(HibernateConfig.class);

    @Autowired
    private org.springframework.core.env.Environment env;

    @Autowired
    PropertiesHolder propertiesHolder;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[] {"com.gmail.sasha.myproject.dao"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(propertiesHolder.getDataBaseDriverName()));
        dataSource.setUrl(Preconditions.checkNotNull(propertiesHolder.getDataBaseURL()));
       // dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername(Preconditions.checkNotNull(propertiesHolder.getDataBaseUsername()));
        dataSource.setPassword(Preconditions.checkNotNull(propertiesHolder.getDataBasePassword()));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO,
                env.getProperty(Environment.HBM2DDL_AUTO));
        /*hibernateProperties.setProperty(
                Environment.CURRENT_SESSION_CONTEXT_CLASS,
                ConfigurationManager.getInstance().getProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS));*/
        hibernateProperties.setProperty(Environment.DEFAULT_SCHEMA,
                env.getProperty(Environment.DEFAULT_SCHEMA));
        hibernateProperties.setProperty(Environment.SHOW_SQL,
                env.getProperty(Environment.SHOW_SQL));
        hibernateProperties.setProperty(
                Environment.DIALECT, env.getProperty(Environment.DIALECT));
        return hibernateProperties;
    }

}
