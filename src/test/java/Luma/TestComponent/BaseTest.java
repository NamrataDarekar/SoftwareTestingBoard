package Luma.TestComponent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	@Test
	public WebDriver RunnerUp() throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Luma\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
         
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.manage().window().maximize();
        return driver;
        
	}
	public void launchApplication() throws IOException
	{
		driver=RunnerUp();
		driver.get("https://magento.softwaretestingboard.com/");
		
		WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Fill in the login form
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("namrata@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("namrata#123");
        
//        WebElement capchaField =driver.findElement(By.xpath("//input[@name='captcha[user_login]']"));
//        capchaField.sendKeys("JCnyr");

        // Submit the login form
        WebElement loginBtn = driver.findElement(By.id("send2"));
        loginBtn.click();
	}

}
