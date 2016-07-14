package com.sms800.quickwin.config;
  
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.sms800.quickwin.job.DBMigrationJob;
import com.sms800.quickwin.job.service.DBMigrationJobService;
  
//@Configuration 
public class QuartzConfiguration {
	@Autowired
	DBMigrationJobService dbMigrationJobServiec;
	
	//@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("jobone");
		obj.setTargetMethod("myTask");
		return obj;
	}
	//@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setRepeatInterval(30000);
		stFactory.setRepeatCount(3);
		return stFactory;
	}
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean(){
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(DBMigrationJob.class);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "DB-Migration");
		map.put(DBMigrationJob.COUNT, 1);
		map.put("dbMigrationJobServiec", dbMigrationJobServiec);
		//map.put("batchConfiguration", batchConfiguration);
		
		factory.setJobDataAsMap(map);
		factory.setGroup("mygroup");
		factory.setName("myjob");
		return factory;
	}
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(){
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(30);
		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		//stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
		stFactory.setCronExpression("0/30 * * 1/1 * ? *");
		return stFactory;
	}
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		//scheduler.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
		scheduler.setTriggers(cronTriggerFactoryBean().getObject());
		return scheduler;
	}
}  
 