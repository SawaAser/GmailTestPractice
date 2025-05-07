package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DraftModalWindow extends BasePage {
    @FindBy(xpath = "//div[@role='dialog']//h2//span")
    private WebElement title;

    @FindBy(xpath = "//div[@class='aoD hl' and @tabindex='1']//span")
    private WebElement recipient;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement body;

    @FindBy(xpath = "//div[starts-with(@data-tooltip,'Discard')]")
    private WebElement discard;

    public DraftModalWindow(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(title);
    }

    public String getRecipient() {
        return getText(recipient);
    }

    public String getBody() {
        return getText(body);
    }

    public void deleteOpenDraft() {
        click(discard);
    }
}
