package com.outdd.aiRead.common.web.config;

/**
 * @author vaie
 * @Created 2018/11/29 15:31
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//扫描Mapper  basePackages要精确到source1目录便于进行不同数据源的区分
@MapperScan(basePackages = "com.outdd.aiRead.ui.**.dao", sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class DataSourceTwoConfig {

  @Bean(name = "dataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.novel")
    public DataSource DataSourceOne(){
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

//    @Bean
//    public ServletRegistrationBean druidStatViewServlet() {
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        registrationBean.addInitParameter("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
//        registrationBean.addInitParameter("deny", ""); // IP黑名单 (存在共同时，deny优先于allow)
//        registrationBean.addInitParameter("loginUsername", "admin");
//        registrationBean.addInitParameter("loginPassword", "admin");
//        registrationBean.addInitParameter("resetEnable", "false");
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean druidWebStatViewFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
//        registrationBean.addInitParameter("urlPatterns", "/*");
//        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
//        return registrationBean;
//    }

    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceTwo") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 扫描mapper.xml
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml");
        bean.setMapperLocations(resources);

        return bean.getObject();
    }

    @Bean(name = "dataSourceTransactionManagerTwo")
    public DataSourceTransactionManager dataSourceTransactionManagerOne(@Qualifier("dataSourceTwo") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory)throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
