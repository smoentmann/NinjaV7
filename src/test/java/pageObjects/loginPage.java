package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends BasePage{

	//constructor
	public	loginPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	WebElement btn_login;
	
	
	
	//action methods
	public void setEmail(String Email)
	{
		txt_Email.sendKeys(Email);
	}
	
	public void setPassword(String password)
	{
		txt_password.sendKeys(password);
	}
	
	public void clickLogin()
	{
		btn_login.click();
	}
	
	
	
	
	
}
