package com.gmail.sasha.myproject.dao.config;


import com.zaxxer.hikari.HikariDataSource;
import liquibase.Liquibase;
import liquibase.integration.spring.SpringLiquibase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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
public class DatabaseConfig {

    private static final Logger logger = LogManager.getLogger(DatabaseConfig.class);

    private static final String PROPERTY_NAME_DATA_SOURCE_CLASS_NAME = "dataSourceClassName";
    private static final String PROPERTY_NAME_USER = "user";
    private static final String PROPERTY_NAME_PASSWORD = "password";
    private static final String PROPERTY_NAME_CACHE_PREP_STMTS = "cachePrepStmts";
    private static final String PROPERTY_NAME_PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    private static final String PROPERTY_NAME_PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    private static final String PROPERTY_NAME_USE_SERVER_PREP_STMTS = "useServerPrepStmts";


    private final PropertiesHolder propertiesHolder;

    @Autowired
    public DatabaseConfig(PropertiesHolder propertiesHolder) {
        this.propertiesHolder = propertiesHolder;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setPoolName("My App connection pool");
        dataSource.setMaximumPoolSize(propertiesHolder.getMaxPoolSize());
        dataSource.setJdbcUrl(propertiesHolder.getDataBaseURL());
        dataSource.setDriverClassName(propertiesHolder.getDataBaseDriverName());

        dataSource.addDataSourceProperty(PROPERTY_NAME_DATA_SOURCE_CLASS_NAME,
                propertiesHolder.getDataSourceClass());
        dataSource.addDataSourceProperty(PROPERTY_NAME_USER,
                propertiesHolder.getDataBaseUsername());
        dataSource.addDataSourceProperty(PROPERTY_NAME_PASSWORD,
                propertiesHolder.getDataBasePassword());
        dataSource.addDataSourceProperty(PROPERTY_NAME_CACHE_PREP_STMTS,
                propertiesHolder.getCachePreparedStatements());
        dataSource.addDataSourceProperty(PROPERTY_NAME_PREP_STMT_CACHE_SIZE,
                propertiesHolder.getCachePreparedStatementsSize());
        dataSource.addDataSourceProperty(PROPERTY_NAME_PREP_STMT_CACHE_SQL_LIMIT,
                propertiesHolder.getCachePreparedStatementsSQLLimit());
        dataSource.addDataSourceProperty(PROPERTY_NAME_USE_SERVER_PREP_STMTS,
                propertiesHolder.getUseServerPreparedStatements());
        logger.info("DataSource was successfully configured");

        return dataSource;
    }

  @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource){
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setDropFirst(Boolean.TRUE);
        springLiquibase.setChangeLog("classpath:migration/db.changelog.xml");
        return springLiquibase;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Properties props = new Properties();
        props.put(Environment.DIALECT, propertiesHolder.getDialect());
        props.put(Environment.DEFAULT_SCHEMA, propertiesHolder.getDefaultSchema());
        props.put(Environment.SHOW_SQL, propertiesHolder.getShowSQL());

        props.put(Environment.HBM2DDL_AUTO, propertiesHolder.getHbm2ddlAuto());
        props.put(Environment.GENERATE_STATISTICS, propertiesHolder.getGenerateStatistics());
        props.put(Environment.USE_SECOND_LEVEL_CACHE, propertiesHolder.getUseSecondLevelCache());
        props.put(Environment.CACHE_REGION_FACTORY, propertiesHolder.getCacheRegionFactory());
        sessionFactory.setHibernateProperties(props);
        sessionFactory.setPackagesToScan("com.gmail.sasha.myproject.dao");
        logger.info("Session factory was successfully configured");

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        return new PersistenceExceptionTranslationPostProcessor();
    }

}
