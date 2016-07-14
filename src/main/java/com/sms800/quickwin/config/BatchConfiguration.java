package com.sms800.quickwin.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.sms800.quickwin.batch.Marksheet;
import com.sms800.quickwin.batch.Student;

@Configuration
@EnableBatchProcessing
@ComponentScan
@EnableAutoConfiguration
public class BatchConfiguration {

	@Bean
	BatchConfigurer configurer(@Qualifier("dataSourceqQW")DataSource dataSource){
	  return new DefaultBatchConfigurer(dataSource);
	}
	
    @Bean
    public ItemReader<Student> reader(@Qualifier("dataSourceqWH") DataSource dataSource) {
        
        JdbcCursorItemReader<Student> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql("select areacode, areacode from areacode LIMIT 10 ");
        databaseReader.setRowMapper(new RowMapper<Student>() {
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student stu=new Student();
				stu.setStdId(rs.getString("areacode"));
				stu.setSubMarkOne(rs.getInt("areacode"));
				
				return stu;
			}
		});
		
        return databaseReader;
        
    }
   
    @Bean  
    public ItemWriter<Marksheet> writer(@Qualifier("dataSourceqQW") DataSource dataSource) {
        JdbcBatchItemWriter<Marksheet> writer = new JdbcBatchItemWriter<Marksheet>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Marksheet>());
        writer.setSql("INSERT INTO marksheet (studentId,totalMark) VALUES (:stdId,:totalSubMark)");
        writer.setDataSource(dataSource);
        return writer;
    }
    
    @Bean
    public ItemProcessor<Student, Marksheet> processor() {
        return new DBMigrationProcessor();
    }

    @Bean
    //@Scheduled(fixedRate = 5000)
    public Job createMarkSheet(JobBuilderFactory jobs, Step step) {
        return jobs.get("dbmigration_"+System.currentTimeMillis())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory, ItemReader<Student> reader,
            ItemWriter<Marksheet> writer, ItemProcessor<Student, Marksheet> processor) {
        return stepBuilderFactory.get("step")
                .<Student, Marksheet> chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}
