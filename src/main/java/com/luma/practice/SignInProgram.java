package com.luma.practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;
import nd.PageObject.LoginPage;

public class SignInProgram {
@Test
public void signin()
{
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

        // Implicitly wait for elements to be present
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the website
        driver.get("https://magento.softwaretestingboard.com/");
        
        driver.manage().window().maximize(); 

        // Find and click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        LoginPage loginpg=new LoginPage();
        loginpg.loginApplication("rupali@gmail.com","rupali@123");

        // Submit the login form
        WebElement loginBtn = driver.findElement(By.id("send2"));
        loginBtn.click();
	
	

        // Explicitly wait for the user name element to appear
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(By
        .xpath("//div[@class='panel header']//span[@class='logged-in'][normalize-space()='Welcome, rupali darekar!']")));

        // Validate successful signing
		
		  String expectedUserName = "Welcome, rupali darekar!"; 
		  if(userName.getText().equals(expectedUserName)) 
		  {
		  System.out.println("Successfully signed in with the user: " + expectedUserName); 
		  } 
		  else 
		  {
		  System.out.println("Failed to sign in with the expected user: " + expectedUserName); 
		  }
		 
		  // Find and click on the "sign Out" link 
		  WebElement logOutLink =driver.findElement(By.cssSelector("div[class='panel header'] button[type='button' ")); 
		  logOutLink.click();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  logOutLink=driver.findElement(By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out'] "));
		  logOutLink.click();
	  
    	  // Validate logout message 
		  WebElement logoutMessage =wait.until(ExpectedConditions.visibilityOfElementLocated(By
		  .xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")));
		  
		  if (logoutMessage.getText().equals("You are signed out")) 
		  {
		  System.out.println("Successfully logged out"); 
		  } 
		  else 
		  {
		  System.out.println("Logout failed"); 
		  }
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
		  
		  //stale means no longer page fresh
        // Fill in the login form with invalid credentials
		  try {
		  signInLink.click();
		  }
		  catch(StaleElementReferenceException e)
		  {
			  signInLink = driver.findElement(By.linkText("Sign In"));
		      signInLink.click(); 
		  }
//		  emailField = driver.findElement(By.id("email"));
//	      emailField.sendKeys("rup@gmail.com");
//	      passwordField = driver.findElement(By.id("pass"));
//	      passwordField.sendKeys("rupali123");
		  
	      loginBtn = driver.findElement(By.id("send2"));
	      loginBtn.click();
		  
		  // Explicitly wait for the error message element to appear 
		  WebElement errorMessage =
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		  "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
		  
		  // Capture the screenshot  
		  TakesScreenshot screenshot = (TakesScreenshot) driver; 
		  File srcFile =screenshot.getScreenshotAs(OutputType.FILE); 
		  File destFile = new File("error_msg_script2_screenshot.png"); 
		  try { FileUtils.copyFile(srcFile,
		  destFile); } 
		  catch (IOException e)
		  { e.printStackTrace(); }
		  
		  // Print the error message 
		  System.out.println("Error message: " + errorMessage.getText());
		  
		  
		  
}

}
