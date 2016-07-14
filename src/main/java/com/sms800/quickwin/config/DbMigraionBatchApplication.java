package com.sms800.quickwin.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.sms800.quickwin.job", "com.sms800.quickwin.job.service" })
// @Import({DBConfig.class, QuartzConfiguration.class})
@Import({ DBConfig.class, JobScheduler.class, BatchConfiguration.class })
public class DbMigraionBatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DbMigraionBatchApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TO DO
	}
}
