package nd.TestComp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	static ExtentReports extent;
	@BeforeTest
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("LUMA Website automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Namrata Darekar");
		return extent;
	}
	@Test
	public void initialDemo()
	{
		ExtentTest test =extent.createTest("initial demo");
		System.setProperty("webdriver.chrome.driver", "E:\\automation25\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		System.out.println(driver.getTitle());
		driver.close();
		
		test.fail("Result do not match");
		extent.flush();
	}

}

