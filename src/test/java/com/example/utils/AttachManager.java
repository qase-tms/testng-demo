package com.example.utils;

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
            WebDriver driver = CustomDriverManager.getDriver();

            Qase.attach("Failure Screenshot.png", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
    }
}
