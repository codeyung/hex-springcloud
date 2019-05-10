package com.hex.code.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-07.21:25
 */
@Configuration
@EnableCaching
@EnableTransactionManagement
@MapperScan(basePackages = "com.hex.code.user.dao")
public class DataSourceConfig {
    @Value("${datasource.url}")
    private String dbUrl;
    @Value("${datasource.name}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.initialSize}")
    private int initialSize;
    @Value("${datasource.minIdle}")
    private int minIdle;
    @Value("${datasource.maxActive}")
    private int maxActive;
    @Value("${datasource.maxWait}")
    private int maxWait;
    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${datasource.validationQuery}")
    private String validationQuery;
    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${datasource.filters}")
    private String filters;
    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
//            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);//指定基包
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));//指定xml文件位置

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseColumnLabel(true);//使用列别名替换列名 select user as User
        configuration.setMapUnderscoreToCamelCase(true);//-自动使用驼峰命名属性映射字段   userId    user_id
        sqlSessionFactory.setConfiguration(configuration);


        return sqlSessionFactory.getObject();
    }
}
