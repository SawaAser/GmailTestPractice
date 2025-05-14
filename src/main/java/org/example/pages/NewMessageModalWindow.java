package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        sendText(title, text);
    }

    public void writeRecipient(String recipientEmail) {
        click(recipient);
        sendText(to, recipientEmail);
    }

    public void writeBody(String text) {
        sendText(body, text);
    }

    public void saveAndClose() {
        click(saveAndClose);
    }
}
