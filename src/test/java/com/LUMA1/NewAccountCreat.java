package com.LUMA1;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewAccountCreat {
       public WebDriver driver;
       @BeforeTest
	 public void setup()
	 {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize(); 
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	 }
       
		
       @Test
       public void createAccount()
       {
		//create an new account
		driver.findElement(By.linkText("Create an Account")).click();
				
		//registration form details		
		driver.findElement(By.id("firstname")).sendKeys("Namrata");//
		driver.findElement(By.id("lastname")).sendKeys("Darekar");
		driver.findElement(By.id("email_address")).sendKeys("namrata@gmail.com");
		driver.findElement(By.id("password")).sendKeys("namrata#123");
		driver.findElement(By.id("password-confirmation")).sendKeys("namrata#123");
		driver.findElement(By.cssSelector("button.submit")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
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
        
       @AfterTest
       public void terminatepage()
       {
        driver.quit();
       }

	

}
