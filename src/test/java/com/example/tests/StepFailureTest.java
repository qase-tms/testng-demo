package com.example.tests;

import com.example.utils.CustomDriverManager;
import io.qase.commons.annotation.Step;
import io.qase.testng.Qase;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class StepFailureTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = CustomDriverManager.getDriver();
    }

    @Test
    public void demonstrateStepLevelFailureTracing() {
        System.out.println("Starting test to demonstrate step-level failure tracing");
        
        step1_NavigateToGoogle();
        step2_VerifyPageTitle();
        step3_SearchForSomething();
        step4_ClickOnNonExistentElement(); // This step will fail
        step5_ThisWillNotExecute();
    }

    @Step("Step 1: Navigate to Google homepage")
    public void step1_NavigateToGoogle() {
        System.out.println("Executing Step 1: Navigating to Google");
        driver.get("https://www.google.com");
        
        // Capture screenshot at this step
        captureStepScreenshot("Step1_NavigateToGoogle");
        
        System.out.println("Step 1 completed successfully");
    }

    @Step("Step 2: Verify page title is correct")
    public void step2_VerifyPageTitle() {
        System.out.println("Executing Step 2: Verifying page title");
        
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);
        
        // Capture screenshot at this step
        captureStepScreenshot("Step2_VerifyPageTitle");
        
        Assert.assertEquals(actualTitle, "Google", "Page title should be 'Google'");
        System.out.println("Step 2 completed successfully");
    }

    @Step("Step 3: Search for 'Qase TestOps'")
    public void step3_SearchForSomething() {
        System.out.println("Executing Step 3: Searching for 'Qase TestOps'");
        
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Qase TestOps");
        
        // Capture screenshot at this step
        captureStepScreenshot("Step3_SearchInput");
        
        searchBox.submit();
        
        // Wait a moment for results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Capture screenshot after search
        captureStepScreenshot("Step3_SearchResults");
        
        System.out.println("Step 3 completed successfully");
    }

    @Step("Step 4: Attempt to click on non-existent element (THIS WILL FAIL)")
    public void step4_ClickOnNonExistentElement() {
        System.out.println("Executing Step 4: Attempting to click on non-existent element");
        
        // Capture screenshot before the failure
        captureStepScreenshot("Step4_BeforeFailure");
        
        try {
            // This will fail because the element doesn't exist
            WebElement nonExistentElement = driver.findElement(By.id("this-element-does-not-exist"));
            nonExistentElement.click();
            
            System.out.println("Step 4 completed successfully (this should not print)");
        } catch (Exception e) {
            // Capture screenshot at the moment of failure
            captureStepScreenshot("Step4_FAILURE_POINT");
            
            System.err.println("FAILURE occurred in Step 4: " + e.getMessage());
            
            // Re-throw the exception to fail the test
            Assert.fail("Step 4 failed: Unable to find element with ID 'this-element-does-not-exist'. " +
                       "Screenshot captured at failure point.");
        }
    }

    @Step("Step 5: This step will not execute due to previous failure")
    public void step5_ThisWillNotExecute() {
        System.out.println("Executing Step 5: This should not execute");
        
        captureStepScreenshot("Step5_ShouldNotExecute");
        
        System.out.println("Step 5 completed successfully (this should not print)");
    }

    /**
     * Helper method to capture screenshots at each step
     * This demonstrates step-level screenshot capture for precise failure tracing
     */
    private void captureStepScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String fileName = stepName + "_screenshot.png";
            
            // Attach screenshot to Qase with step-specific naming
            Qase.attach(fileName, screenshot, "image/png");
            
            System.out.println("Screenshot captured for: " + stepName);
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot for " + stepName + ": " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        CustomDriverManager.quitDriver();
    }
}
