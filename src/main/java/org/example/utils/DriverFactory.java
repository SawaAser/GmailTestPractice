package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(String browser) {
        String setBrowser = getBrowserIfDefault(browser);
        WebDriver baseDriver = getDriverByNameBrowser(browser, setBrowser);
        WebDriver decoratedDriver = new EventFiringDecorator<>(new WebEventListener()).decorate(baseDriver);
        driver.set(decoratedDriver);
    }

    private static WebDriver getDriverByNameBrowser(String browser, String setBrowser) {
        return switch (setBrowser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Browser not supported: " + browser);
        };
    }

    private static String getBrowserIfDefault(String browser) {
        String setBrowser;
        if (browser != null && !browser.equals("default")) {
            setBrowser = browser;
        } else {
            setBrowser = Config.getDefaultBrowser();
        }
        return setBrowser;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
