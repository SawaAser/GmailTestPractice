package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class DraftsPage extends BasePage {
    @FindBy(xpath = "//div[@role='main']//tr//div[@role='link']")
    private List<WebElement> draftEmails;

    public DraftsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isNotEmptyDraftEmails() {
        return !draftEmails.isEmpty();
    }

    public DraftModalWindow showDraftModalWindowByTitle(String title) {
        WebElement element = draftEmails.stream()
                .filter(draft -> getText(draft).startsWith(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No draft email found with title: " + title));
        click(element);
        return new DraftModalWindow(driver);
    }
}
