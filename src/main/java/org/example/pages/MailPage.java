package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailPage extends BasePage {
    @FindBy(xpath = "//div[@class='ii gt']")
    private WebElement bodyMassage;

    @FindBy(xpath = "//span[@class='go']")
    private WebElement senderEmail;

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public boolean textMailContains(String text) {
        wait.until(ExpectedConditions.visibilityOf(bodyMassage));
        return getText(bodyMassage).contains(text);
    }

    public String getSenderEmail() {
        wait.until(ExpectedConditions.visibilityOf(senderEmail));
        String email = getText(senderEmail);
        return email.substring(1, email.length()-1);
    }
}
