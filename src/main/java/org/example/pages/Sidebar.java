package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Sidebar  extends BasePage {
    @FindBy(xpath = "//div[text()='Compose' or text()='Написати']")
    private WebElement composeButton;

    @FindBy(xpath = "//a[starts-with(@aria-label, 'Drafts')]")
    private WebElement draftsButton;

    public Sidebar(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public NewMessageModalWindow showNewMessageModalWindow() {
        wait.until(ExpectedConditions.elementToBeClickable(composeButton));
        click(composeButton);
        return new NewMessageModalWindow(driver);
    }

    public DraftsPage goToDraftsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(draftsButton));
        click(draftsButton);
        wait.until(ExpectedConditions.titleContains("Draft"));
        return new DraftsPage(driver);
    }
}
