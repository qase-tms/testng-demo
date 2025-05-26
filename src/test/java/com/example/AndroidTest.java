package com.example;

import java.io.IOException;
import com.example.utils.AppiumServerManager;
import com.example.utils.CustomAppiumDriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class AndroidTest {
    private AppiumDriver driver;

    AppiumServerManager serverManager = new AppiumServerManager();

    @BeforeSuite
    public void startAppium() {
        try {
            serverManager.startServer();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start Appium server", e);
        }
    }


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        driver = CustomAppiumDriverManager.getDriver();
    }

    @Test
    public void testApiDemosLaunchSuccess() {
        System.out.println("Simulating ApiDemos app launch test...");

        WebElement accessibilityElement = driver.findElement(
                AppiumBy.accessibilityId("Accessibility"));
        accessibilityElement.click();

        WebElement nodeQueryingElement = driver.findElement(
                AppiumBy.accessibilityId("Accessibility Node Querying"));

        assert nodeQueryingElement.isDisplayed() : "Failed to navigate to Accessibility section";
    }

    @Test
    public void testApiDemosLaunchFailed() {
        System.out.println("Simulating ApiDemos app launch test...");

        WebElement accessibilityElement = driver.findElement(
                AppiumBy.accessibilityId("Accessibility"));
        accessibilityElement.click();

        WebElement nodeQueryingElement = driver.findElement(
                AppiumBy.accessibilityId("Accessibility Node Querying (not exist)"));

        assert nodeQueryingElement.isDisplayed() : "Failed to navigate to Accessibility section";
    }

    @AfterMethod
    public void tearDown() {
        CustomAppiumDriverManager.quitDriver();
    }

    @AfterSuite
    public void stopAppium() {
        serverManager.stopServer();
    }
}
