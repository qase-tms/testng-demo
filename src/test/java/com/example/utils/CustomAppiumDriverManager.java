package com.example.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class CustomAppiumDriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() throws MalformedURLException {
        if (driver.get() == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("Android Emulator");
            File app = new File("apps/ApiDemos-debug.apk");
            options.setApp(app.getAbsolutePath());
            options.setAutomationName("UiAutomator2");
            options.setPlatformName("Android");
            options.setFullReset(false);

            URL appiumServerURL = new URL("http://localhost:4723");

            AppiumDriver instance = new AndroidDriver(appiumServerURL, options);
            instance.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.set(instance);
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
