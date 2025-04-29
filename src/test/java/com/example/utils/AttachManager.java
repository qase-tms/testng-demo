package com.example.utils;

import io.appium.java_client.AppiumDriver;
import io.qase.commons.hooks.HooksListener;
import io.qase.commons.models.domain.TestResult;
import io.qase.commons.models.domain.TestResultStatus;
import io.qase.testng.Qase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class AttachManager implements HooksListener {

    @Override
    public void beforeTestStop(final TestResult result) {
        if (result.execution.status == TestResultStatus.FAILED) {
            try {
                // Assuming you have a method to get the current AppiumDriver instance
                AppiumDriver appiumDriver = CustomAppiumDriverManager.getDriver();

                // Attach screenshot
                Qase.attach("Failure Screenshot.png", ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES), "image/png");
            } catch (Exception e) {
                // Handle exception
            }
        }
    }
}
