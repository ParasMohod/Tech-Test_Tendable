package com.listeners.techchallenge;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.initialization.driver.DriverFactory;

public class ExtentReportListener extends DriverFactory implements ITestListener {

	public WebDriver driver;
	
	public ExtentReportListener(WebDriver driver) {
		this.driver=driver;
	}

	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";
	
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_FOLDER);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("Automation Test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "Windows");
		extentReports.setSystemInfo("Author", "Paras Mohod");
		extentReports.setSystemInfo("Build#", "1.1");
		return extentReports;
	}
	
	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;
	
	
		
	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println("Test Suite started!");
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " failed!"));
		try {
			test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot(driver)).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

}
