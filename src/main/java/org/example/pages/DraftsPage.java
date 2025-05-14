package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;

public class DraftsPage extends BasePage {
    private static final By DRAFT_EMAILS_LOCATOR = By.xpath("//div[@role='main']//tr//div[@role='link']");

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getDraftEmails() {
        return driver.findElements(DRAFT_EMAILS_LOCATOR);
    }

    public boolean isEmptyDraftEmails() {
        try {
            shortWait.until(ExpectedConditions.presenceOfElementLocated(DRAFT_EMAILS_LOCATOR));
        } catch (TimeoutException e) {
            return true;
        }
        return getDraftEmails().isEmpty();
    }

    public DraftModalWindow showDraftModalWindowByTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DRAFT_EMAILS_LOCATOR));
        WebElement element = getDraftEmails().stream()
                .filter(draft -> getText(draft).startsWith(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No draft email found with title: " + title));
        click(element);
        return new DraftModalWindow(driver);
    }
}
