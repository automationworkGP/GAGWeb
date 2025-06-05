package com.sigma.Listener;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener , ISuiteListener{

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("🔷 Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getMethod().getMethodName());
        
        System.out.println("Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Test Suite Finished: " + context.getName());
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
