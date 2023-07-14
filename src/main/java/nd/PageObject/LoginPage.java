package nd.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage()
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(id="email")
    WebElement emailField;
    
    @FindBy(id="pass")
    WebElement passwordField;
    
    @FindBy(id="send2")
    WebElement loginBtn;
    
    public void loginApplication(String email,String pswd)
    {
    	emailField.sendKeys(email);
    	passwordField.sendKeys(pswd);
    	loginBtn.click();
    }
    

}
