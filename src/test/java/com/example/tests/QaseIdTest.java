package com.example.tests;

import io.qase.commons.annotation.*;
import org.testng.annotations.Test;

public class QaseIdTest {
    @Test
    // Replace the Ids `1, 2 3` with a valid Id from your Qase project

    @QaseId(1)
    public void testWithQaseId_success() {
        System.out.println("Test with QaseId");
    }

    @QaseIds({ 1, 2, 3 })
    public void testWithMultipleQaseId_success() {
        System.out.println("Test with QaseId");
    }
}
