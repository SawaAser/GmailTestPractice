package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class InboxPage extends BasePage {
    @FindBy(xpath = "//div[@role='tabpanel']//tr")
    private List<WebElement> incomeEmails;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailPresent(String partName) {
        return incomeEmails.stream()
                .anyMatch(email -> getText(email).contains(partName));
    }

    public WebElement getMatchingEmail(String partName) {
        return incomeEmails.stream()
                .filter(email -> getText(email).contains(partName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Email with text containing '" + partName + "' not found."));
    }

    public MailPage goToMailByNameContains(String partName) {
        WebElement matchingEmail = getMatchingEmail(partName);
        click(matchingEmail);
        return new MailPage(driver);
    }

}
