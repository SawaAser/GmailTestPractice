package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.Config;
import org.example.pages.InboxPage;
import org.example.pages.LoginPage;
import org.example.pages.Sidebar;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected String EMAIL = Config.getEmail();
    protected String PASSWORD = Config.getPassword();
    protected LoginPage loginPage;
    protected InboxPage inboxPage;
    protected Sidebar sidebar;

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        getDriver().get(Config.getBaseUrl());
        getDriver().manage().window().maximize();
        loginPage = new LoginPage(getDriver());
        inboxPage = loginPage.login(EMAIL, PASSWORD);
        sidebar = new Sidebar(getDriver());
    }

    @AfterMethod
    public void takeScreenshotForFailures(ITestResult testResult) {
        if (driver != null && testResult.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);

            File screenshotsDir = new File(System.getProperty("user.dir") + "/resources/screenshots/");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File destination = new File(screenshotsDir, testResult.getName() + "_" + timestamp + ".png");

            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // очищення ThreadLocal
        }
    }
}

