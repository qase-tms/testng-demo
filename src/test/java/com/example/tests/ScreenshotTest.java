package com.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(com.example.utils.TestListener.class)
public class ScreenshotTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return this.driver; // for screenshot capturing listener
    }
}
