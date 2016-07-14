package com.sms800.quickwin.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.sms800.quickwin.job.service.DBMigrationJobService;

@Configuration 
@Import({DBMigrationJobService.class})
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DBMigrationJob extends QuartzJobBean {

	DBMigrationJobService dbMigrationJobService;
	
	public static final String COUNT = "count";
	private String name;
	
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
    	JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
    	int cnt = dataMap.getInt(COUNT);
		JobKey jobKey = ctx.getJobDetail().getKey();
		dbMigrationJobService=(DBMigrationJobService) dataMap.get("dbMigrationJobServiec");
		System.out.println(jobKey+": "+name+": "+ cnt + " : Migraion Service Instance :"+dbMigrationJobService);
		
		if(dbMigrationJobService.executeJob()){
			System.out.println("Job Execute Successfully");
		} else{
			System.out.println("Job Execution Failed");
		}
		
		cnt++;
		dataMap.put(COUNT, cnt);
    }
    
	public void setName(String name) {
		this.name = name;
	}
}
