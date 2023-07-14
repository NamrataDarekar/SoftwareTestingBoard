package com.luma;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ShoppingCart {
	WebDriver driver;
	@BeforeTest
    public void setUp() {
		driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://magento.softwaretestingboard.com/");

        driver.manage().window().maximize(); 
        
    }
	@Test
	public  void ShoppingCart()throws InterruptedException {
        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Implicitly wait for elements to be present
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the website
        driver.get("https://magento.softwaretestingboard.com/");

        driver.manage().window().maximize(); 

        // Find and click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("rupali@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("rupali@123");
        
        WebElement loginBtn = driver.findElement(By.id("send2"));
        loginBtn.click();

         //Scroll down to the "What's new" section
        
        WebElement whatsnew = driver.findElement(By.id("ui-id-3"));
        whatsnew.click();
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        
        WebDriverWait wait= new  WebDriverWait(driver, Duration.ofSeconds(20));
        //driver.findElement(By.xpath("(//span[normalize-space()='What's New'])[1]"));
        //jsExecutor.executeScript("window.scrollTo(1,document.body.scrollHeight)");
        
        jsExecutor.executeScript("window.scrollBy(0,1200)");//scrolldown
        

        // Add an item from the "What's new" section
        
        driver.findElement(By.cssSelector("a[title='Gobi HeatTec&reg; Tee']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'XS')]")).click();
        driver.findElement(By.xpath("//div[@aria-label='Black']")).click();
        
        WebElement addToCartBtn = driver.findElement(By.cssSelector("button#product-addtocart-button"));
        addToCartBtn.click();
        
        jsExecutor.executeScript("window.scrollBy(0,-400)");//scrollup
        
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        
        // Open a new tab and navigate to "Gear and Watches" section
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://magento.softwaretestingboard.com/gear/watches.html");
        
         
		//another way to navigate to "Gear and Watches" section
		/*
		 * WebElement GearLink =driver.findElement(By.xpath("//span[text()='Gear']"));
		 * Actions act=new Actions(driver); act.moveToElement(GearLink).perform();
		 * driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		 * driver.findElement(By.xpath("//span[text()='Watches']")).click();
		 */
		 


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
        	       .xpath("//*[@id='narrow-by-list']/div[3]//div[2]/ol/li[3]/a"));
        	        System.out.println(rubberFilter.isDisplayed());
        	        rubberFilter.click();

        // Add a product listed after applying the filters(ss)
        jsExecutor.executeScript("window.scrollBy(0,300)");
        WebElement product = driver.findElement(By
        .xpath("//body/div[1]/main[1]/div[3]/div[1]/div[3]/ol[1]/li[2]/div[1]/div[1]/div[3]/div[1]/div[1]/form[1]/button[1]"));
        System.out.println(product.isDisplayed());
        System.out.println(product.isEnabled());
        jsExecutor.executeScript("arguments[0].click();",product);
        
        //product.click();    //(//span[contains(text(),'Add to Cart')])[2]
        // Capture the screenshot  
		  TakesScreenshot screenshot = (TakesScreenshot) driver; 
		  File srcFile =screenshot.getScreenshotAs(OutputType.FILE); 
		  File destFile = new File("AddToCart_screenshot.png"); 
		  try { FileUtils.copyFile(srcFile,
		  destFile); } 
		  catch (IOException e)
		  { e.printStackTrace(); }
        
       // Close the current tab
        //driver.close();
        
        //driver.navigate().back();

        // Switch back to the default tab
        //driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        // Refresh the default tab
        //driver.navigate().refresh();
        driver.switchTo().defaultContent();
        
        //driver.quit();
        
        
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

        WebElement addToCartBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@title='Add to Cart'])[1]")));
        addToCartBtn1.click();
        
        // Step 6: Navigate to cart
        driver.navigate().refresh();
          driver.navigate().to("https://magento.softwaretestingboard.com/checkout/cart");
          //driver.navigate().refresh();
          
          jsExecutor.executeScript("window.scrollBy(0,500)");
          
          driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
          
        // Step 7: Increase the quantity of the pants from 1 to 4
       WebElement quantityInput = wait.until(ExpectedConditions.elementToBeClickable(By
       .xpath("(//input[@class='input-text qty'])[3]")));
  
        System.out.println("pant is added to cart: " +quantityInput.isDisplayed());
        
        quantityInput.clear();
        quantityInput.sendKeys("4");
        quantityInput.submit();
        
        // Step 8: Assert total Cart value and click on the checkout
        Actions actions = new Actions(driver);
        
        WebElement SubTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By
        .cssSelector("tr.totals.sub span")));
        
        WebElement discount = wait.until(ExpectedConditions.visibilityOfElementLocated(By
        .cssSelector("tr.totals td[data-th='Discount'] span.price")));
        
//        WebElement shipping= wait.until(ExpectedConditions.visibilityOfElementLocated(By
//        .cssSelector("tr.excl td.amount span")));
        
        WebElement OrderTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By
        .xpath("//td[@data-th='Order Total']//span")));
        Assert.assertTrue(OrderTotal.isDisplayed());
        
        String cartTotalValue = OrderTotal.getText();
//        driver.navigate().refresh();
//        String cartSubTotalValue=SubTotal.getText();
//        
//        String cartDisTotalValue=discount.getText();
//        //String cartShipTotalValue=shipping.getText();
//        String cartTotalValue=OrderTotal.getText();
        System.out.println("Order Total: " + cartTotalValue);
        
        Assert.assertNotEquals(OrderTotal,cartTotalValue);

        driver.navigate().refresh();
        
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By
        .xpath("//button[@class='action primary checkout' and @data-role='proceed-to-checkout']")));
        checkoutBtn.click();
        
        
        
     // Step 9: Fill the required details on the checkout page
//        driver.findElement(By.name("street[0]")).sendKeys("ABC");
//        
//        driver.findElement(By.name("city")).sendKeys("Los Angeles");
//        
//        jsExecutor.executeScript("window.scrollBy(0,600)");
//        
//        WebElement countrydrp=driver.findElement(By.name("country_id"));
//        Select DD1=new Select(countrydrp);
//        DD1.selectByVisibleText("United States");
//        
//        WebElement statedrp=driver.findElement(By.name("region_id"));
//        Select DD=new Select(statedrp);
//        DD.selectByVisibleText("California");
//        
//        driver.findElement(By.name("postcode")).sendKeys("90011");
//        driver.findElement(By.name("telephone")).sendKeys("+1(321)5555-6780");
        
     // Step 9.1: Default address book saved
        driver.findElement(By.xpath("//div[@class='shipping-address-item selected-item']")).isDisplayed();
        

        // Step 10: Select the Table rate Radio button and Click on the Next button
        WebElement tableRateRadio =driver.findElement(By.xpath("//td[@id='label_method_bestway_tablerate']"));
        tableRateRadio.click();

        WebElement nextBtn =driver.findElement(By
        .xpath("//button[@class='button action continue primary']"));
        nextBtn.click();

        // Step 19: Validate the Cart value with Step no 15

        WebElement cartValueInCheckout = wait.until(ExpectedConditions.elementToBeClickable
        (By.xpath("//td[@data-th='Order Total']//span")));//tr[@class='grand totals']/td
        //span.estimated-price
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
	
	@AfterTest
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

	
}
