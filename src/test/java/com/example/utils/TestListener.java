package com.example.utils;

import io.qase.testng.Qase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = null;

        try {
            driver = (WebDriver) testClass.getClass().getMethod("getDriver").invoke(testClass);
        } catch (Exception e) {
            System.err.println("[ERROR] Unable to get WebDriver instance: " + e.getMessage());
            return;
        }

        if (driver == null) {
            System.err.println("[ERROR] WebDriver instance is NULL! Skipping screenshot.");
            return;
        }

        String screenshotPath = captureScreenshot(driver, result.getName());
        if (screenshotPath != null) {
            // Attach screenshot to TestNG Report
            Reporter.log("<a href='" + screenshotPath + "'> <img src='" + screenshotPath
                    + "' height='100' width='100'/> </a>");

            // Attach screenshot to Qase
            Qase.attach("Failure Screenshot", screenshotPath, "image/png");

            System.out.println("[INFO] Screenshot saved and attached to Qase: " + screenshotPath);
        }
    }

    private String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Generate timestamped screenshot name
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = System.getProperty("user.dir") + "/target/surefire-reports/failure_screenshots/";
            File screenshotFolder = new File(screenshotDir);
            if (!screenshotFolder.exists())
                screenshotFolder.mkdirs(); // Ensure folder exists

            String screenshotName = "screenshot_" + testName + "_" + timestamp + ".png";
            File destFile = new File(screenshotDir + screenshotName);

            // Take a screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, destFile);

            return destFile.getAbsolutePath(); // Return file path for Qase attachment
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to save screenshot: " + e.getMessage());
        }
        return null;
    }
}
