package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

   

    @FindBy(xpath = "//strong[normalize-space()='login page']")
    WebElement loginPageLink;

    @FindBy(id = "input-shipping-address")
    WebElement shippingAddressDropdown;

    @FindBy(id = "button-shipping-methods")
    WebElement shippingMethodsButton;

    @FindBy(id = "button-shipping-method")
    WebElement flatShippingButton;

    @FindBy(id = "button-payment-methods")
    WebElement paymentMethodsButton;

    @FindBy(id = "button-payment-method")
    WebElement codButton;

    @FindBy(xpath = "//div[@class='text-end']//button[contains(text(),'Confirm')]")
    WebElement confirmButton;

    

    public void clickLogin() {
        loginPageLink.click();
       
    }

    public void completeCheckout() throws InterruptedException {
    	//Thread.sleep(5000);
        new Select(shippingAddressDropdown).selectByIndex(1);
        shippingMethodsButton.click();
        Thread.sleep(5000);
        flatShippingButton.click();
        Thread.sleep(10000);
        paymentMethodsButton.click();
        codButton.click();
        scroll(confirmButton);
        Thread.sleep(5000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmButton);
        //confirmButton.click();
    }

    
   

   

    private void scroll(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
       // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}


