package com.luma;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NewAccountCreat {
     public WebDriver driver;
	@BeforeClass
    public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize(); 
	}
		//create an new account
	@Test
	public void account() throws InterruptedException
	{
		driver.findElement(By.linkText("Create an Account")).click();
				
		//registration form details		
		driver.findElement(By.id("firstname")).sendKeys("Namrata");//
		driver.findElement(By.id("lastname")).sendKeys("Darekar");
		driver.findElement(By.id("email_address")).sendKeys("namrata@gmail.com");
		driver.findElement(By.id("password")).sendKeys("namrata#123");
		driver.findElement(By.id("password-confirmation")).sendKeys("namrata#123");
		driver.findElement(By.cssSelector("button.submit")).click();
		Thread.sleep(3000);
		//successfully create new account message.
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(120));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By
        .xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        
        // Print the error message
        System.out.println("Error message: " + errorMessage.getText());
        
        // Capture the screenshot
        driver.manage().window().maximize(); 
        
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("error_message_screenshot.png");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ss captured");
	}
	
	@AfterClass
	public void tearDown()
	{
        driver.close();
	}

	}


