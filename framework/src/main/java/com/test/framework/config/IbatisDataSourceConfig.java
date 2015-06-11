package com.test.framework.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan("com.test.*.dao")
@PropertySource({ "file:${configPath}/${spring.profiles.active}/datasource.properties" })
public class IbatisDataSourceConfig {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	
	 @Bean
	    public DataSource getDataSource() {
	       BasicDataSource dataSource = new BasicDataSource();
	       dataSource.setDriverClassName(driver);
	       dataSource.setUrl(url);
	       dataSource.setUsername(username);
	       dataSource.setPassword(password);
	       return dataSource;
	   }
	   @Bean
	   public DataSourceTransactionManager transactionManager() {
	       return new DataSourceTransactionManager(getDataSource());
	   }
	   @Bean
	   public SqlSessionFactory getSqlSessionFactory() throws Exception {
	      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	      sessionFactory.setDataSource(getDataSource());
	      return sessionFactory.getObject();
	   }
	   
	   @Bean
	   public SqlSessionTemplate sqlSessionTemplate() throws Exception{
		   SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(getSqlSessionFactory()); 
		   return sqlSessionTemplate;
	   }

}
