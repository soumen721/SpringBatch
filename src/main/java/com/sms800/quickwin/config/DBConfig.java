package com.sms800.quickwin.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DBConfig {

	@Bean(name="jdbcTemplateQW")
	@Primary
	public JdbcTemplate jdbcTemplateQW(@Qualifier("dataSourceqQW") DataSource dataSource) {
		return new JdbcTemplate(getDataSourceQW());
	}

	@Bean(name="jdbcTemplateWH")
	public JdbcTemplate jdbcTemplateWH(@Qualifier("dataSourceqWH") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "dataSourceqQW")
	@Primary
	public DataSource getDataSourceQW() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springbatch?characterEncoding=UTF-8&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}

	@Bean(name = "dataSourceqWH")
	public DataSource getDataSourceWH() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/dbmigration?characterEncoding=UTF-8&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}

}
