package com.LUMA1;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingScript extends SignInProgram  {
          public WebDriver driver;
        // Create a new instance of ChromeDriver
//        WebDriver driver = new ChromeDriver();
//
//        // Implicitly wait for elements to be present
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        // Navigate to the website
//        driver.get("https://magento.softwaretestingboard.com/");
//
//        driver.manage().window().maximize(); 

        // Find and click on the "Sign In" link
//        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
//        signInLink.click();
//
//        WebElement emailField = driver.findElement(By.id("email"));
//        emailField.sendKeys("namrata@gmail.com");
//
//        WebElement passwordField = driver.findElement(By.id("pass"));
//        passwordField.sendKeys("namrata#123");
//        
//        WebElement loginBtn = driver.findElement(By.id("send2"));
//        loginBtn.click();

         //Scroll down to the "What's new" section
        @Test
        public void whatsNew()
        {
        WebElement whatsnew = driver.findElement(By.id("ui-id-3"));
        whatsnew.click();
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        driver.findElement(By.xpath("(//span[normalize-space()='What's New'])[1]"));
        //jsExecutor.executeScript("window.scrollTo(1,document.body.scrollHeight)");
        
        jsExecutor.executeScript("window.scrollBy(0,1200)");//scrolldown
        

        // Add an item from the "What's new" section
        driver.findElement(By.cssSelector("a[title='Gobi HeatTec&reg; Tee']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'XS')]")).click();
        driver.findElement(By.xpath("//div[@aria-label='Black']")).click();
        
        WebElement addToCartBtn = driver.findElement(By.cssSelector("button#product-addtocart-button"));
        addToCartBtn.click();
        
        jsExecutor.executeScript("window.scrollBy(0,-400)");//scrollup
        
        // Open a new tab and navigate to "Gear and Watches" section
        driver. switchTo(). newWindow(WindowType.TAB);
        driver.get("https://magento.softwaretestingboard.com/gear/watches.html");
        

//		/*another way to navigate to "Gear and Watches" section
//		 * WebElement GearLink =driver.findElement(By.xpath("//span[text()='Gear']"));
//		 * Actions act=new Actions(driver); act.moveToElement(GearLink).perform();
//		 * Thread.sleep(3000);
//		 * driver.findElement(By.xpath("//span[text()='Watches']")).click();
//		 */
//

       // Select the price filter
        WebElement priceFilter = driver.findElement(By.xpath("//div[normalize-space()='Price']"));
        priceFilter.click();

        // Select the $40.00 - $49.99 price range
        WebElement priceRangeFilter = driver.findElement(By
        		.xpath("//*[@id='narrow-by-list']/div[2]/div[2]/ol/li[1]/a/span[1]"));
        WebDriverWait w= new  WebDriverWait(driver, Duration.ofSeconds(20));
        w.until(ExpectedConditions.visibilityOfElementLocated(By
        		.xpath("//*[@id='narrow-by-list']/div[2]/div[1]")));
         priceRangeFilter.click();
         
         jsExecutor.executeScript("window.scrollBy(0,300)");

        // Select the material filter
         WebElement materialFilter = driver.findElement(By.xpath("//div[normalize-space()='Material']"));
         materialFilter.click();

        // Select the Rubber material
         WebElement rubberFilter = driver.findElement(By
        	       .xpath("//*[@id=\"narrow-by-list\"]/div[3]//div[2]/ol/li[3]/a"));
        	        System.out.println(rubberFilter.isDisplayed());
        	        rubberFilter.click();

        // Add a product listed after applying the filters(ss)
        WebElement product = driver.findElement(By
        .xpath("//li[2]//div[1]//div[1]//div[3]//div[1]//div[1]//form[1]//button[1]//span[1]"));
        product.click();    //(//span[contains(text(),'Add to Cart')])[2]
     // Capture the screenshot  
		  TakesScreenshot screenshot = (TakesScreenshot) driver; 
		  File srcFile =screenshot.getScreenshotAs(OutputType.FILE); 
		  File destFile = new File("AddToCart_screenshot.png"); 
		  try { FileUtils.copyFile(srcFile,
		  destFile); } 
		  catch (IOException e)
		  { e.printStackTrace(); }
        
        // Switch back to the original tab
        //driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        // Close the current tab
        driver.close();

        // Switch back to the default tab
        //driver.switchTo().defaultContent();
        //driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        // Refresh the default tab
        driver.navigate().refresh();
    }
    }
