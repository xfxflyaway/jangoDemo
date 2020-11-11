/**
 * Copyright (c) @2015,重庆云途交通科技有限公司.版权所有
 */
package com.jango.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@MapperScan("com.jango.demo.mapper")
public class MybatisConfig {

    // 注入配置
    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dataSourceConfig.getUrl());
        ds.setUsername(dataSourceConfig.getUsername());
        ds.setPassword(dataSourceConfig.getPassword());
        ds.setInitialSize(dataSourceConfig.getInitConnSize());
        ds.setMinIdle(dataSourceConfig.getMinIdleConnSize());
        ds.setMaxActive(dataSourceConfig.getMaxActiveConnSize());
        ds.setTimeBetweenEvictionRunsMillis(60000);
        ds.setDriverClassName(dataSourceConfig.getDriver());
        ds.setMaxWait(60000);
        ds.setMinEvictableIdleTimeMillis(300000);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);
        ds.setTestWhileIdle(true);
        ds.setValidationQuery("SELECT 'x'");
        ds.setPoolPreparedStatements(false);
        return ds;
    }

    // spring 事务
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean
                .setConfigLocation(new ClassPathResource("/cfg/mybatis/mybatis-config.xml"));
        Resource[] mapperResources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/jango/demo/mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(mapperResources);
        return sqlSessionFactoryBean;
    }


    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加IP白名单
//        servletRegistrationBean.addInitParameter("allow", "192.168.25.125,127.0.0.1");
        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
//        servletRegistrationBean.addInitParameter("deny", "192.168.25.123");
        // 添加控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return filterRegistrationBean;
    }


}
