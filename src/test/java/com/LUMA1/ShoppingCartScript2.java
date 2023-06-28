package com.LUMA1;

import org.apache.commons.io.FileUtils;
//import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartScript2 extends SignInProgram{
	static WebDriver driver;
    
    	
        // Create a new instance of ChromeDriver
//       driver = new ChromeDriver();
//
//        // Implicitly wait for elements to be present
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        // Navigate to the website
//        driver.get("https://magento.softwaretestingboard.com/");
//
//        driver.manage().window().maximize(); 
//
//        // Find and click on the "Sign In" link
//        WebElement signInLink = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/ul/li[2]/a"));
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
         
	@Test
	public void mensSection()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        
        WebDriverWait wait= new  WebDriverWait(driver, Duration.ofSeconds(20));

     // Step 2: Navigate to Men's section
        WebElement mensSection = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Men")));
        mensSection.click();

        // Step 3: Navigate to Bottoms and then pants
        WebElement bottomsSection = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bottoms")));
        Actions act=new Actions(driver); 
        act.moveToElement(bottomsSection).perform();

        WebElement pants = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Pants")));
        pants.click();

         //Step 4: Sort the Items by lowest price
        Select sortDropdown = new Select(driver.findElement(By.id("sorter")));
        sortDropdown.selectByValue("price");
        
        jsExecutor.executeScript("window.scrollBy(0,400)");

        // Step 5: Add the First Item in the cart Waist size 34 and colour Green
        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(By
        .xpath("//a[@class='product-item-link'][normalize-space()='Mithra Warmup Pant']")));
        firstItem.click();
        
        //driver.findElement(By.xpath("//a[text()='Mithra Warmup Pant']")).click();
        
        jsExecutor.executeScript("window.scrollBy(0,200)");
        
        WebElement waistSize = wait.until(ExpectedConditions.elementToBeClickable(By.id("option-label-size-143-item-177")));
        waistSize.click();

        WebElement color = wait.until(ExpectedConditions.elementToBeClickable(By.id("option-label-color-93-item-53")));
        color.click();

        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@title='Add to Cart'])[1]")));
        addToCartBtn.click();
        
        // Step 6: Navigate to cart
        
          driver.navigate().to("https://magento.softwaretestingboard.com/checkout/cart");
          driver.navigate().refresh();

        // Step 7: Increase the quantity of the pants from 1 to 4
        WebElement quantityInput = wait.until(ExpectedConditions.elementToBeClickable(By
        		.cssSelector("input.qty")));
        quantityInput.clear();
        quantityInput.sendKeys("4");
        quantityInput.submit();

        // Step 8: Assert total Cart value and click on the checkout
        WebElement cartTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By
        .xpath("(//span[@data-bind='text: getValue()'][normalize-space()='$112.00'])[1]")));
        
        String cartTotalValue = cartTotal.getText();
        System.out.println("Cart Total: " + cartTotalValue);

        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By
        .xpath("//button[@class='action primary checkout' and @data-role='proceed-to-checkout']")));
        checkoutBtn.click();
        
        // Step 9.1: Default address book saved
        driver.findElement(By.xpath("//div[@class='shipping-address-item selected-item']")).isDisplayed();
        
     // Step 9: Fill the required details on the checkout page
//        driver.findElement(By.name("street[0]")).sendKeys("PCMC");
//        
//        driver.findElement(By.name("city")).sendKeys("pune");
//        
//        jsExecutor.executeScript("window.scrollBy(0,600)");
//        
//        WebElement countrydrp=driver.findElement(By.name("country_id"));
//        Select DD1=new Select(countrydrp);
//        DD1.selectByVisibleText("India");
//        
//        WebElement statedrp=driver.findElement(By.name("region_id"));
//        Select DD=new Select(statedrp);
//        DD.selectByVisibleText("Maharashtra");
//        
//        driver.findElement(By.name("postcode")).sendKeys("411260");
//        driver.findElement(By.name("telephone")).sendKeys("7823495612");
//        

        // Step 10: Select the Table rate Radio button and Click on the Next button
        WebElement tableRateRadio =driver.findElement(By.name("ko_unique_1"));
        tableRateRadio.click();

        WebElement nextBtn =driver.findElement(By
        .xpath("//button[@class='button action continue primary']"));
        nextBtn.click();

        // Step 19: Validate the Cart value with Step no 15

        WebElement cartValueInCheckout = wait.until(ExpectedConditions.elementToBeClickable
        (By.xpath("//tr[@class='grand totals']/td")));
        String cartValueCheckout = cartValueInCheckout.getText();
        System.out.println("Cart value in Checkout: " + cartValueCheckout);


        // Step 20: Click on the place order
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By
        .cssSelector("button[title='Place Order']")));

        System.out.println(placeOrderButton.isDisplayed());
        System.out.println(placeOrderButton.isEnabled());
        jsExecutor.executeScript("arguments[0].click();",placeOrderButton);

        // Validate the Thank you message and Order number
        WebElement thankYouMessage = driver.findElement(By.xpath("//h1[@class='page-title']"));
        
        WebElement orderNumber= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'order-number')]")));
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
       //stale element not found
        try {
        	String thankYouMessageText = thankYouMessage.getText();
        	String orderNumberText = orderNumber.getText();
  		  }
  		  catch(StaleElementReferenceException e)
  		  {
  			thankYouMessage = driver.findElement(By.xpath("//h1[@class='page-title']"));
  			String thankYouMessageText = thankYouMessage.getText(); 
  			
  			orderNumber= wait.until(ExpectedConditions.visibilityOfElementLocated
  			(By.xpath("//a[contains(@class,'order-number')]")));
  			String orderNumberText = orderNumber.getText();
  		  }
        catch(WebDriverException we)
        {
        	we.printStackTrace();
        }
        System.out.println("Thank you message: " + thankYouMessage.getText());
        System.out.println("Order number: " + orderNumber.getText());
       

    }
    

    }
