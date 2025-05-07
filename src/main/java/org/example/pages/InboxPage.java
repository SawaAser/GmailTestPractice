package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class InboxPage extends BasePage {
    @FindBy(xpath = "//div[@role='tabpanel']//tr")
    private List<WebElement> incomeEmails;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNotEmptyEmails() {
        return !incomeEmails.isEmpty();
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
        wait.until(ExpectedConditions.elementToBeClickable(matchingEmail));
        click(matchingEmail);
        return new MailPage(driver);
    }

}
