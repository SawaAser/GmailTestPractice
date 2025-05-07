package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getBaseUrl() {
        return dotenv.get("BASE_URL");
    }

    public static String getEmail() {
        return dotenv.get("EMAIL");
    }

    public static String getPassword() {
        return dotenv.get("PASSWORD");
    }

    public static String getSenderEmail() {
        return dotenv.get("EMAIL_2");
    }
}
