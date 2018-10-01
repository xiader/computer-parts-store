package com.gmail.sasha.myproject.dao.config;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PropertiesHolder {

    private static final String DATABASE = "database";
    private static final String DATABASE_DRIVER_NAME = DATABASE + ".driver.name";
    private static final String DATABASE_URL = DATABASE + ".url";
    private static final String DATABASE_USERNAME = DATABASE + ".username";
    private static final String DATABASE_PWD = DATABASE + ".password";
    private static final String POOL_CACHE_PREPARED_STATEMENTS = "pool.cache.prepared.statements";
    private static final String POOL_DATA_SOURCE_CLASS = "pool.data.source.class";
    private static final String POOL_MAX_SIZE = "pool.max.size";
    private static final String POOL_CACHE_PREPARED_STATEMENTS_SIZE = POOL_CACHE_PREPARED_STATEMENTS + ".size";
    private static final String POOL_CACHE_PREPARED_STATEMENTS_SQL_LIMIT = POOL_CACHE_PREPARED_STATEMENTS + ".sql.limit";
    private static final String POOL_USE_SERVER_PREPARED_STATEMENTS = "pool.use.server.prepared.statements";
    private static final String HIBERNATE = "hibernate";
    private static final String HIBERNATE_DIALECT = HIBERNATE + ".dialect";
    private static final String HIBERNATE_DEFAULT_SCHEMA = HIBERNATE + ".default_schema";
    private static final String HIBERNATE_SHOW_SQL = HIBERNATE + ".show_sql";
    private static final String HIBERNATE_HBM_2_DDL_AUTO = HIBERNATE + ".hbm2ddl.auto";

    private final Environment env;

    private String dataBaseDriverName;
    private String dataBaseURL;
    private String dataBaseUsername;
    private String dataBasePassword;
    //connection pooling
    private int maxPoolSize;
    private String dataSourceClass;
    private String cachePreparedStatements;
    private String cachePreparedStatementsSize;
    private String cachePreparedStatementsSQLLimit;
    private String useServerPreparedStatements;
    //hibernate
    private String dialect;
    private String defaultSchema;
    private String showSQL;
    private String hbm2ddlAuto;
    private String generateStatistics;
    private String useSecondLevelCache;
    private String cacheRegionFactory;

    @Autowired
    public PropertiesHolder(Environment env) {
        this.env = env;
    }


    @PostConstruct
    public void init() {
        this.dataBaseDriverName = env.getProperty(DATABASE_DRIVER_NAME);
        this.dataBaseURL = env.getProperty(DATABASE_URL);
        this.dataBaseUsername = env.getProperty(DATABASE_USERNAME);
        this.dataBasePassword = env.getProperty(DATABASE_PWD);
        this.maxPoolSize = Integer.parseInt(Preconditions.checkNotNull(env.getProperty(POOL_MAX_SIZE)));
        this.dataSourceClass = env.getProperty(POOL_DATA_SOURCE_CLASS);
        this.cachePreparedStatements = env.getProperty(POOL_CACHE_PREPARED_STATEMENTS);
        this.cachePreparedStatementsSize = env.getProperty(POOL_CACHE_PREPARED_STATEMENTS_SIZE);
        this.cachePreparedStatementsSQLLimit = env.getProperty(POOL_CACHE_PREPARED_STATEMENTS_SQL_LIMIT);
        this.useServerPreparedStatements = env.getProperty(POOL_USE_SERVER_PREPARED_STATEMENTS);
        this.dialect = env.getProperty(HIBERNATE_DIALECT);
        this.defaultSchema = env.getProperty(HIBERNATE_DEFAULT_SCHEMA);
        this.showSQL = env.getProperty(HIBERNATE_SHOW_SQL);
        this.hbm2ddlAuto = env.getProperty(HIBERNATE_HBM_2_DDL_AUTO);
        this.generateStatistics = env.getProperty("hibernate.generate_statistics");
        this.useSecondLevelCache = env.getProperty("hibernate.cache.use_second_level_cache");
        this.cacheRegionFactory = env.getProperty("hibernate.cache.region.factory_class");
    }

    public String getDataBaseDriverName() {
        return dataBaseDriverName;
    }

    public void setDataBaseDriverName(String dataBaseDriverName) {
        this.dataBaseDriverName = dataBaseDriverName;
    }

    public String getDataBaseURL() {
        return dataBaseURL;
    }

    public void setDataBaseURL(String dataBaseURL) {
        this.dataBaseURL = dataBaseURL;
    }

    public String getDataBaseUsername() {
        return dataBaseUsername;
    }

    public void setDataBaseUsername(String dataBaseUsername) {
        this.dataBaseUsername = dataBaseUsername;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public void setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public void setDataSourceClass(String dataSourceClass) {
        this.dataSourceClass = dataSourceClass;
    }

    public String getCachePreparedStatements() {
        return cachePreparedStatements;
    }

    public void setCachePreparedStatements(String cachePreparedStatements) {
        this.cachePreparedStatements = cachePreparedStatements;
    }

    public String getCachePreparedStatementsSize() {
        return cachePreparedStatementsSize;
    }

    public void setCachePreparedStatementsSize(String cachePreparedStatementsSize) {
        this.cachePreparedStatementsSize = cachePreparedStatementsSize;
    }

    public String getCachePreparedStatementsSQLLimit() {
        return cachePreparedStatementsSQLLimit;
    }

    public void setCachePreparedStatementsSQLLimit(String cachePreparedStatementsSQLLimit) {
        this.cachePreparedStatementsSQLLimit = cachePreparedStatementsSQLLimit;
    }

    public String getUseServerPreparedStatements() {
        return useServerPreparedStatements;
    }

    public void setUseServerPreparedStatements(String useServerPreparedStatements) {
        this.useServerPreparedStatements = useServerPreparedStatements;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    public String getShowSQL() {
        return showSQL;
    }

    public void setShowSQL(String showSQL) {
        this.showSQL = showSQL;
    }

    public String getHbm2ddlAuto() {
        return hbm2ddlAuto;
    }

    public void setHbm2ddlAuto(String hbm2ddlAuto) {
        this.hbm2ddlAuto = hbm2ddlAuto;
    }

    public String getGenerateStatistics() {
        return generateStatistics;
    }

    public void setGenerateStatistics(String generateStatistics) {
        this.generateStatistics = generateStatistics;
    }

    public String getUseSecondLevelCache() {
        return useSecondLevelCache;
    }

    public void setUseSecondLevelCache(String useSecondLevelCache) {
        this.useSecondLevelCache = useSecondLevelCache;
    }

    public String getCacheRegionFactory() {
        return cacheRegionFactory;
    }

    public void setCacheRegionFactory(String cacheRegionFactory) {
        this.cacheRegionFactory = cacheRegionFactory;
    }
}
