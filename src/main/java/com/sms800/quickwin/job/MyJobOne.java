package com.sms800.quickwin.job;

import org.springframework.stereotype.Service;

@Service("jobone")
public class MyJobOne {
    protected void myTask() {
    	System.out.println("This is my task");
    }
}
