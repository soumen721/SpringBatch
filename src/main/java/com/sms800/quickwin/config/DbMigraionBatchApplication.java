package com.sms800.quickwin.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sms800.quickwin.batch.Marksheet;

@SpringBootApplication
@EnableAutoConfiguration
@Import({BatchConfiguration.class})
public class DbMigraionBatchApplication implements CommandLineRunner{

	@Bean
	BatchConfigurer configurer(@Qualifier("dataSourceqQW")DataSource dataSource){
	  return new DefaultBatchConfigurer(dataSource);
	}
	
	@Autowired
	@Qualifier("dataSourceqWH")
	JdbcTemplate dataSourceqWH;
	
	@Autowired
	@Qualifier("dataSourceqQW")
	JdbcTemplate dataSourceqQW;
	
	public static void main(String[] args) {
		SpringApplication.run(DbMigraionBatchApplication.class, args);
	}

	
    @Override
	public void run(String... arg0) throws Exception {
    	/*List<Marksheet> result = dataSourceqWH.query("select studentId,totalMark FROM marksheet", new RowMapper<Marksheet>() {
            @Override
            public Marksheet mapRow(ResultSet rs, int row) throws SQLException {
                return new Marksheet(rs.getString(1), Integer.parseInt(rs.getString(2)));
            }
        });
        System.out.println("Number of Record:"+result.size());*/
	}
}

