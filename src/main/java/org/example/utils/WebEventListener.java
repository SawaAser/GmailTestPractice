package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebEventListener implements WebDriverListener {
    private static final Logger logger = LogManager.getLogger(WebEventListener.class);

    @Override
    public void beforeGet(WebDriver driver, String url) {
        logger.info("Navigating to: {}", url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.debug("Successfully navigated to: {}", url);
    }

    @Override
    public void beforeClick(WebElement element) {
        logger.info("About to click element: {}", describeElement(element));
    }

    @Override
    public void afterClick(WebElement element) {
        logger.debug("Clicked element: {}", describeElement(element));
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("Sending keys to element: {} | Keys: {}", describeElement(element), String.join("", keysToSend));
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.debug("Keys sent to element: {}", describeElement(element));
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        logger.error("Error occurred during method invocation: {}", method.getName(), e.getTargetException());
    }

    private String describeElement(WebElement element) {
        try {
            return element.toString();
        } catch (Exception e) {
            return "unknown element";
        }
    }
}