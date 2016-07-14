package com.sms800.quickwin.config;

import org.springframework.boot.SpringApplication;

//@EnableScheduling
public class JobRunner {

  //@Scheduled(fixedRate = 5000)
  public void findAndRunJob() {
    SpringApplication.run(BatchConfiguration.class);
  }
}