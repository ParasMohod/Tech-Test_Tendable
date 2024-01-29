package com.initialization.driver;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	private int count = 0;
	private static int maxTry = 3;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { 
			if (count<maxTry) { 
				count++;
				iTestResult.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
		}
		return false;
	}
}
