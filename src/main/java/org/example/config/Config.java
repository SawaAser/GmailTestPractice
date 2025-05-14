package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties configProps = new Properties();
    private static final Properties credentialsProps = new Properties();

    static {
        try (FileInputStream configInput = new FileInputStream("src/main/resources/config.properties")) {
            configProps.load(configInput);
        } catch (IOException e) {
            System.err.println("Could not load config.properties");
            e.printStackTrace();
        }

        try (FileInputStream credentialsInput = new FileInputStream("src/main/resources/credentials.properties")) {
            credentialsProps.load(credentialsInput);
        } catch (IOException e) {
            System.err.println("Could not load credentials.properties");
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return configProps.getProperty("BASE_URL");
    }

    public static String getDefaultBrowser() {
        return configProps.getProperty("BROWSER");
    }

    public static String getEmail() {
        return credentialsProps.getProperty("EMAIL");
    }

    public static String getPassword() {
        return credentialsProps.getProperty("PASSWORD");
    }

    public static String getSenderEmail() {
        return credentialsProps.getProperty("EMAIL_2");
    }
}
