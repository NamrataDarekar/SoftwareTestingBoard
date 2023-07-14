package nd.TestComp;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

	public class Listeners implements ITestListener{
		ExtentTest test;
		ExtentReports extent = ExtentReport.getReportObject();
		ThreadLocal<ExtentTest> ET= new ThreadLocal<ExtentTest>();//paraller running classes
		
		@Override
		public void onTestStart (ITestResult result) {
			test = extent. createTest (result.getMethod () . getMethodName ( ) ) ;
			ET.set(test);
		}
		
		
		@Override
		public void onTestSuccess (ITestResult result) {
		// TODO Auto-generated method stub
		ET.get(). log(Status.PASS,"Test Passed");
		}
		@Override
		public void onTestFailure (ITestResult result) {
		ET.get().fail(result.getThrowable ());
		//Screenshot
//		String filepath = null;
//		try {
//		filepath = getScreenshot(result.getMethod() .getMethodName () ) ;
//		}
//		catch (IOException e) { 
//		e.printStackTrace ();
//		test. addScreenCaptureFromPath(filepath, result. getMethod() . getMethodName ());
//		}
		}
		
		@Override
		public void onTestSkipped(ITestResult result) {
	    ExtentReports extent =ExtentReport.getReportObject();
		}
		
		@Override
		public void onTestFailedButWithinSuccessPercentage (ITestResult result) {
		// TODO Auto-generated method stub
		}
		
		@Override
		public void onStart (ITestContext context) {
		// TODO Auto-generated method stub
		}
		
		@Override
		public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		}

}
