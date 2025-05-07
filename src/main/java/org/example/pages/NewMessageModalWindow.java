package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewMessageModalWindow extends BasePage{
    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement title;

    @FindBy(xpath = "//div[@tabindex='1']")
    private WebElement recipient;

    @FindBy(xpath = "//div[@name='to']//input")
    private WebElement to;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement body;

    @FindBy(xpath = "//img[@aria-label='Save & close']")
    private WebElement saveAndClose;

    public NewMessageModalWindow(WebDriver driver) {
        super(driver);
    }

    public void writeTitle(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(title));
        write(title, text);
    }

    public void writeRecipient(String recipientEmail) {
        click(recipient);
        write(to, recipientEmail);
    }

    public void writeBody(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(body));
        write(body, text);
    }

    public void saveAndClose() {
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose));
        click(saveAndClose);
    }
}
