package com.sms800.quickwin.config;

import org.springframework.batch.item.ItemProcessor;

import com.sms800.quickwin.batch.Marksheet;
import com.sms800.quickwin.batch.Student;

public class StudentItemProcessor implements ItemProcessor<Student, Marksheet> {

    @Override
    public Marksheet process(final Student student) throws Exception {
    	int totalMark = student.getSubMarkOne()+student.getSubMarkTwo();
    	System.out.println("student id:"+student.getStdId() +" and Total mark:"+ totalMark);
    	Marksheet marksheet = new Marksheet(student.getStdId(), totalMark);
        return marksheet;
    }

}
