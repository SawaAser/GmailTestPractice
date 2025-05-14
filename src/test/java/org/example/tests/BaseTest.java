package org.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.Config;
import org.example.pages.InboxPage;
import org.example.pages.LoginPage;
import org.example.pages.Sidebar;
import org.example.utils.DriverFactory;
import org.example.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class BaseTest {
    WebDriver driver;

    protected LoginPage loginPage;
    protected InboxPage inboxPage;
    protected Sidebar sidebar;

    protected final String EMAIL = Config.getEmail();
    protected final String PASSWORD = Config.getPassword();

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("default") String browserParam) {
        DriverFactory.setDriver(browserParam);
        driver = DriverFactory.getDriver();
        driver.get(Config.getBaseUrl());
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inboxPage = loginPage.login(EMAIL, PASSWORD);
        sidebar = new Sidebar(driver);
        logger.info("-----Start TEST CLASS-----");
    }

    @BeforeMethod
    public void startMethod(ITestResult testResult) {
        logger.info("-----Start TEST ----- {}", testResult.getMethod().getMethodName());
    }

    @AfterMethod
    public void afterEachTest(ITestResult testResult) {
        logger.info("-----End TEST ----- {}", testResult.getMethod().getMethodName());

        if (testResult.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.takeScreenshot(driver, testResult.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
        logger.info("-----End TEST CLASS-----");
    }
}

