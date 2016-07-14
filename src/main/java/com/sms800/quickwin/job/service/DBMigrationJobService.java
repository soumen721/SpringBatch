package com.sms800.quickwin.job.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class DBMigrationJobService {
	/*@Bean
	BatchConfigurer configurer(@Qualifier("dataSourceqQW")DataSource dataSource){
	  return new DefaultBatchConfigurer(dataSource);
	}*/
	
	final String selectQuery="select areacode, areacode from areacode LIMIT 10  ";
	String insertQuery="INSERT INTO marksheet (studentId,totalMark) VALUES (?, ?)";
	
	@Autowired
	@Qualifier("jdbcTemplateQW")
	JdbcTemplate jdbcTemplateQW;
	
	@Autowired
	@Qualifier("jdbcTemplateWH")
	JdbcTemplate jdbcTemplateWH;
	
	public boolean executeJob(){
		
		try{
			List<List<String>> result = jdbcTemplateWH.query(selectQuery, new RowMapper<List<String>>() {
	            @Override
	            public List<String> mapRow(ResultSet rs, int row) throws SQLException {
	            	List<String> list=new ArrayList<String>();
	            	list.add(rs.getString("areacode"));
	            	list.add(rs.getString("areacode"));
	            	
	            	return list;
	            }
	        });
			
			if(result!=null && !result.isEmpty()){
				for (List<String> columns : result) {
					jdbcTemplateQW.update(insertQuery, new Object[] { Integer.parseInt(columns.get(0)), columns.get(1)});
					System.out.println("Data Insert successfully to marksheetTable :: with student Id: "+ columns.get(0));
				}
			}
		} catch(Exception exc){
			System.out.println("Job Execution Failed");
			return false;
		}
		return true;
	}
	
}
