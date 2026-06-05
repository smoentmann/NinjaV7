package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage extends BasePage {
	
	
	public homePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	
	//locators
	@FindBy(xpath="//i[@class='fa-solid fa-user']")
	WebElement link_myAccount;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement link_login;
	
	
	
	
	
	//action methods
	public void clickmyAccount()
	{
		link_myAccount.click();
	}
	
	public void goToLogin()
	{
		link_login.click();
	}
}
