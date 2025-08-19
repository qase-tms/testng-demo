package com.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {

    @Test
    public void demoTest() {
        System.out.println("✅ Running SampleTest.demoTest()");
        Assert.assertTrue(true, "This test always passes!");
    }
}