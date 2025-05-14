package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(css = "input[type='email']")
    private WebElement emailInput;

    @FindBy(css = "input[type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//span[text()='Далі' or text()='Next']//ancestor::button")
    private WebElement nextButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage login(String email, String password) {
        sendText(emailInput, email);
        click(nextButton);
        sendText(passwordInput, password);
        click(nextButton);
        return new InboxPage(driver);
    }
}
