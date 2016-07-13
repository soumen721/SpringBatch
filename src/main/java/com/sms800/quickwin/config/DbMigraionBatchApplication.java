package com.sms800.quickwin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@Import({JobRunner.class})
public class DbMigraionBatchApplication implements CommandLineRunner{

	
	
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

