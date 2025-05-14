package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailPage extends BasePage {
    @FindBy(xpath = "//div[@class='ii gt']")
    private WebElement bodyMassage;

    @FindBy(xpath = "//span[@class='go']")
    private WebElement senderEmail;

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public boolean textMailContains(String text) {
        return getText(bodyMassage).contains(text);
    }

    public String getSenderEmail() {
        String email = getText(senderEmail);
        return email.substring(1, email.length()-1);
    }
}
