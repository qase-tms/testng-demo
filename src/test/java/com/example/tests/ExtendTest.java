package com.example.tests;

import com.example.App;
import io.qase.testng.Qase;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utils.ZipUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtendTest {

    private static ExtentReports extent;
    private static ExtentTest test;

    private static final String REPORT_PATH = System.getProperty("user.dir") + "/reports/ExtentReport.html";

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void Test_without_any_Qase_annotations() {
        test = extent.createTest("Test_without_any_Qase_annotations");

        // Execute the test logic
        App app = new App();
        int result = app.add(2, 3);
        Assert.assertEquals(result, 5, "Expected 2 + 3 to equal 5");

        // Flush report and attach to Qase
        extent.flush();
        Qase.attach(REPORT_PATH);
    }

    @AfterSuite
    public void afterSuite() {
        // Optional: zip the report folder
        try {
            ZipUtil.zipFolder(System.getProperty("user.dir") + "/reports",
                              System.getProperty("user.dir") + "/ExtentReport.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
