package com.example.tests;

import com.example.utils.CustomDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ScreenshotTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = CustomDriverManager.getDriver();
    }

    @Test
    public void googleTitleTest() {
        driver.get("https://www.google.com");
        String actualTitle = driver.getTitle();
        System.out.println("Page Title: " + actualTitle);

        // Failing the test here..
        Assert.assertEquals(actualTitle, "This Title is Wrong!", "Test failed: Incorrect page title.");
    }

    @AfterClass
    public void tearDown() {
        CustomDriverManager.quitDriver();
    }
}
